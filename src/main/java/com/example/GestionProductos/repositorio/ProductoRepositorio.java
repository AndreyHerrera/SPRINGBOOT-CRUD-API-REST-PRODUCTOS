package com.example.GestionProductos.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.GestionProductos.entidades.Producto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepositorio extends JpaRepository <Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE CONCAT(p.id, p.nombre, p.fabricante, p.precio) LIKE %?1% ")
    public List<Producto> buscarProducto(String palabraClave);
}
