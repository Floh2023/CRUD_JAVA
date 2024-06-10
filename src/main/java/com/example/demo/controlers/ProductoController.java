package com.example.demo.controlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;



import com.example.demo.repositories.ProductoRepository;
import com.example.demo.entities.Producto;


@RestController
@RequestMapping("/productos")

public class ProductoController {

  @Autowired
  private ProductoRepository productoRepository;

  @GetMapping
  public List<Producto> getAllProductos(){
    return productoRepository.findAll();
  }

  @PostMapping
  public Producto createProducto(@RequestBody Producto producto){

    return productoRepository.save(producto);

  }

  @GetMapping("/{id}")
  public Producto obtenerProductoPorId(@PathVariable Long id){
    return productoRepository.findById(id)
    .orElseThrow(()-> new RuntimeException("No se encontro el producto"+id));


  }


  @PutMapping("/{id}")
  public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detallesProducto){

    Producto producto = productoRepository.findById(id)
    .orElseThrow(()-> new RuntimeException("No se encontro el producto"+id));
    producto.setNombre(detallesProducto.getNombre());
    producto.setPrecio(detallesProducto.getPrecio());

    return productoRepository.save(producto);

  }

  @DeleteMapping("/{id}")
  public Producto deleteProducto(@PathVariable Long id){

    Producto producto = productoRepository.findById(id)
    .orElseThrow(()-> new RuntimeException("No se encontro el producto"+id));

    productoRepository.delete(producto);

    return "el producto con el id: " + id + "fue eliminado ";

  }

}
