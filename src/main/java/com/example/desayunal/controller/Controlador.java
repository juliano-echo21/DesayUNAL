package com.example.desayunal.controller;

import com.example.desayunal.model.Producto;
import com.example.desayunal.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping
public class Controlador {
    @Autowired
    private ProductoService service;

    @Autowired
    private JdbcTemplate jdbc;

    @GetMapping("/listar")
    public String listar(Model model){
        List<Producto> productos = service.listar();
        model.addAttribute("productos",productos);
        return "index";
    }

    @GetMapping("/new")
    public String agregar(Model model){
        model.addAttribute("producto",new Producto());
        return "form";
    }

    @PostMapping("/save")
    public String save(Producto p,Model model, @RequestParam("file") MultipartFile file) throws IOException {

        service.save(p);
        if (!file.isEmpty()) {

            String sql = "SELECT max(id) FROM producto";
            int idProducto = jdbc.queryForObject(sql,Integer.class);

            sql = "INSERT INTO imagen (idProducto, nombre, tipo, tamano, pixel) VALUES(?, ?, ?, ?, ?)";

            String nombre = file.getOriginalFilename();
            String tipo   = file.getContentType();
            Long tamano   = file.getSize();
            byte[] pixel  = file.getBytes();


            jdbc.update(sql, idProducto,nombre, tipo, tamano, pixel);
        }
        return "redirect:/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model){
        Optional<Producto> producto = service.listarId(id);
        model.addAttribute("producto",producto);
        return "form";
    }

    @GetMapping("eliminar/{id}")
    public String delete(Model model, @PathVariable int id){
        service.delete(id);
        return "redirect:/listar";
    }

    @RequestMapping(value = "uploaded/{id}")
    public void getUploadedPicture(
            @PathVariable int id, HttpServletResponse response)
            throws IOException {
        String sql = "SELECT pixel, tipo FROM imagen WHERE idProducto = '" + id + "'";
        List<Map<String, Object>> result = jdbc.queryForList(sql);

        if (!result.isEmpty()) {
            byte[] bytes = (byte[]) result.get(0).get("PIXEL");
            String mime = (String) result.get(0).get("TIPO");

            response.setHeader("Content-Type", mime);
            response.getOutputStream().write(bytes);
        }
    }
}
