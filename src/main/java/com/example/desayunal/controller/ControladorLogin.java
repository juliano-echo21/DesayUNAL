package com.example.desayunal.controller;

import com.example.desayunal.services.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class ControladorLogin {

    @Autowired
    private ServicioUsuario servicio;

    @GetMapping
    public String loggear(String userName, String password, Model model) {
        return "login";
    }
}
