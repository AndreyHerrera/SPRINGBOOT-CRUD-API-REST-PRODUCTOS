package com.example.GestionProductos.controlador;

import com.example.GestionProductos.entidades.Producto;
import com.example.GestionProductos.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @RequestMapping("/")
    public String paginaInicio(Model modelo, @Param("palabraClave") String palabraClave){
        List<Producto> verListaProductos = productoServicio.verListaProductos(palabraClave);

        modelo.addAttribute("palabraClave", palabraClave);
        modelo.addAttribute("verListaProductos", verListaProductos);

        return "index";
    }

    @RequestMapping("/NuevoProducto")
    public String nuevoProducto(Model modelo){
        Producto producto = new Producto();
        modelo.addAttribute("producto",producto);
        return "nuevoProducto";
    }

    @RequestMapping(value = "/guardarProducto", method = RequestMethod.POST)
    public String guardarProducto(@ModelAttribute("producto") Producto producto){
        productoServicio.crearProducto(producto);
        return "redirect:/";
    }

    @RequestMapping("/EditarProducto/{id}")
    public ModelAndView editarProducto(@PathVariable(name = "id") Long id){
        ModelAndView modelo = new ModelAndView();
        modelo.setViewName("editarProducto");
        Producto producto  = productoServicio.verProducto(id);
        modelo.addObject("producto", producto);
        return modelo;
    }

    @RequestMapping("/EliminarProducto/{id}")
    public String eliminarProducto(@PathVariable(name = "id") Long id){
        productoServicio.eliminarProducto(id);
        return "redirect:/";
    }


}
