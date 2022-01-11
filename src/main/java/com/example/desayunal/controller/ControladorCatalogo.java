package com.example.desayunal.controller;

import java.util.List;

import com.example.desayunal.model.Producto;
import com.example.desayunal.services.ServicioProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class ControladorCatalogo{
    @Autowired
    private ServicioProducto sProducto;

    @Autowired
    private JdbcTemplate jdbc;

    @GetMapping("/desayunal")
    public String listarDisponibles(Model model){
        return listarDisponibles(model, "todos");
    }
    @GetMapping("/desayunal/{id}")
    public String listarDisponibles(Model model, @PathVariable String id){
        List<Producto> productos = sProducto.listarDisponibles();
        String nLista = "Todos los productos";

        switch(id){
            case "ofertas":
                productos = sProducto.listarEstado("Oferta");
                nLista = "Ofertas";
                break;
            case "desayunos":
                productos = sProducto.listarCategoriaDisponible("Desayuno");
                nLista = "Desayunos";
                break;
            case "postres":
                productos  = sProducto.listarCategoriaDisponible("Postre");
                nLista = "Postres";
                break;
            case "onces":
                productos = sProducto.listarCategoriaDisponible("Onces");
                nLista = "Onces";
                break;
        }

        model.addAttribute("productos",productos);
        model.addAttribute("nLista", nLista);
        model.addAttribute("page", "catalogo");
        return "catalogo";
    }

}