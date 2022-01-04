package com.example.desayunal.controller;

import java.util.List;

import com.example.desayunal.model.Producto;
import com.example.desayunal.servicios.ServicioProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<Producto> productos = sProducto.listarDisponibles();
        List<Producto> ofertas = sProducto.listarEstado("Oferta");
        List<Producto> desayunos = sProducto.listarCategoriaDisponible("Desayuno");
        List<Producto> postres = sProducto.listarCategoriaDisponible("Postre");
        List<Producto> onces = sProducto.listarCategoriaDisponible("Onces");
        
        model.addAttribute("productos",productos);
        model.addAttribute("ofertas", ofertas);
        model.addAttribute("postres", postres);
        model.addAttribute("desayunos", desayunos);
        model.addAttribute("onces", onces);
        return "catalogo";
    }

}