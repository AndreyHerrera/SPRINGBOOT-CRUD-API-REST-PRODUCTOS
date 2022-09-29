package com.example.GestionProductos.servicio;

import com.example.GestionProductos.entidades.Producto;
import com.example.GestionProductos.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    public Producto verProducto(Long id){
        return productoRepositorio.findById(id).get();
    }

    public List<Producto> verListaProductos(String palabraClave){
        if (palabraClave != null){
            return productoRepositorio.buscarProducto(palabraClave);
        }
        else {
            return productoRepositorio.findAll();
        }

    }

    public void crearProducto(Producto producto){
        productoRepositorio.save(producto);
    }

    public void eliminarProducto(Long id){
        productoRepositorio.deleteById(id);
    }
}
