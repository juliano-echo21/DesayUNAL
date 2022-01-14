package com.example.desayunal.controller;

import com.example.desayunal.model.Producto;
import com.example.desayunal.services.ServicioProducto;

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
public class ControladorAdminProductos {
    @Autowired
    private ServicioProducto service;

    @Autowired
    private JdbcTemplate jdbc;

    private boolean editando = false;

    @GetMapping("/listar")
    public String listar(Model model){
        List<Producto> productos = service.listar();
        model.addAttribute("productos",productos);
        model.addAttribute("page", "admin");
        return "listarProductos";
    }

    @GetMapping("/new")
    public String agregar(Model model){
        model.addAttribute("producto",new Producto());
        return "formularioProductos";
    }

    @PostMapping("/save")
    public String guardar(Producto p, Model model, @RequestParam("file") MultipartFile file) throws IOException {
        
        int res = service.guardar(p,editando);

        if(!editando && res != 1)
            return "redirect:new?existProduct";
        if (!file.isEmpty()) {


            int idProducto = p.getId();
            String nombre = file.getOriginalFilename();
            String tipo   = file.getContentType();
            Long tamano   = file.getSize();
            byte[] pixel  = file.getBytes();

            String sql = "SELECT count(nombre) FROM imagen WHERE id_producto = "+p.getId();
            int n = jdbc.queryForObject(sql,Integer.class);
            if(n != 0) {
                sql = "UPDATE imagen SET nombre = ?, tipo = ?, tamano = ?, pixel = ? WHERE id_producto = ?";
                jdbc.update(sql, nombre, tipo, tamano, pixel,p.getId());
            }else{
                sql = "INSERT INTO imagen (id_producto, nombre, tipo, tamano, pixel) VALUES(?, ?, ?, ?, ?)";
                jdbc.update(sql, idProducto, nombre, tipo, tamano, pixel);
            }


        }
        editando = false;
        return "redirect:/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model){
        editando = true;
        Optional<Producto> producto = service.listarId(id);
        model.addAttribute("producto",producto);
        return "formularioProductos";
    }

    @GetMapping("eliminar/{id}")
    public String eliminar(Model model, @PathVariable int id){
        String sql = "DELETE FROM imagen WHERE id_producto = ?";
        jdbc.update(sql,id);
        service.eliminar(id);
        return "redirect:/listar";
    }

    @RequestMapping(value = "uploaded/{id}")
    public void getUploadedPicture(
            @PathVariable int id, HttpServletResponse response)
            throws IOException {
        String sql = "SELECT pixel, tipo FROM imagen WHERE id_producto = '" + id + "'";
        List<Map<String, Object>> result = jdbc.queryForList(sql);

        if (!result.isEmpty()) {
            byte[] bytes = (byte[]) result.get(0).get("PIXEL");
            String mime = (String) result.get(0).get("TIPO");

            response.setHeader("Content-Type", mime);
            response.getOutputStream().write(bytes);
        }
    }
}
