package com.nosql.nosql.controller;

import com.nosql.nosql.clases.Carrito;
import com.nosql.nosql.clases.DtDatosProducto;
import com.nosql.nosql.clases.Producto;
import com.nosql.nosql.clases.excepciones.ExcepcionNotFound;
import com.nosql.nosql.repository.CarritoRepository;
import com.nosql.nosql.repository.ProductoRepository;
import com.nosql.nosql.repository.SesionRepository;
import com.nosql.nosql.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    CarritoRepository carritoRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    SesionRepository sesionRepository;

    @PostMapping("/{sesion}")
    public ResponseEntity<Object> agregarAlCarrito (@RequestBody DtDatosProducto productoInfo, @PathVariable(value = "sesion") String sesion) {
        Map<String, Object> datosSesion = sesionRepository.findAll(UUID.fromString(sesion));
        if(!datosSesion.isEmpty()){
            String idcarrito = (String) datosSesion.get("carrito");
            Carrito carrito = carritoRepository.findById(UUID.fromString(idcarrito));
            List<DtDatosProducto> productos = carrito.getProductos();
            Producto producto = productoRepository.findById(UUID.fromString(productoInfo.getId()));
            if(producto ==null)
                throw new ExcepcionNotFound("No existe el producto");
            boolean encontrado= false;
            if(productoInfo.getCantidad() > producto.getStock()){
                throw new ExcepcionNotFound("No hay suficiente stock disponible");
            }
            for(DtDatosProducto carritoProd : productos) {
                if(Objects.equals(carritoProd.getId(), productoInfo.getId())){
                    carritoProd.setCantidad(productoInfo.getCantidad());
                    encontrado=true;
                    break;
                }
            }
            if (!encontrado) {
                productoInfo.setNombre(producto.getNombre());
                productos.add(productoInfo);
                carrito.setProductos(productos);
            }
            carritoRepository.save(carrito);
            return ResponseHandler.generateResponse("Producto agregado del carrito con éxito", HttpStatus.OK, null);
        }else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Debe existir una sesion activa");
        }
    }
    @GetMapping("/{sesion}")
    public List<DtDatosProducto> obtenerProductosCarrito (@PathVariable(value = "sesion") String sesion){
        Map<String, Object> datosSesion = sesionRepository.findAll(UUID.fromString(sesion));
        if(!datosSesion.isEmpty()) {
            String idcarrito = (String) datosSesion.get("carrito");
            Map<String, Carrito> carrito = carritoRepository.findAll();
            return carrito.get(idcarrito).getProductos();
        }else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Debe exisitir una sesion activa");
        }
    }
    @DeleteMapping("/{sesion}/{producto}")
    public ResponseEntity<Object> borrardelcarrito(@PathVariable(value = "sesion") String sesion, @PathVariable(value = "producto") String idproducto){
        Map<String, Object> datosSesion = sesionRepository.findAll(UUID.fromString(sesion));
        if(!datosSesion.isEmpty()) {
            String idcarrito = (String) datosSesion.get("carrito");
            Carrito carrito = carritoRepository.findById(UUID.fromString(idcarrito));
            List<DtDatosProducto> productos = carrito.getProductos();
            productos.removeIf(producto -> producto.getId().equals(idproducto));
            carritoRepository.save(carrito);
            return ResponseHandler.generateResponse("Producto quitado del carrito con éxito", HttpStatus.OK, null);
        }else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
