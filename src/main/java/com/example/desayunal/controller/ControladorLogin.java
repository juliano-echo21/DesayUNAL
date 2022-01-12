package com.example.desayunal.controller;

import com.example.desayunal.services.ServicioUsuario;
import com.example.desayunal.web.dto.RegistroUsuarioDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class ControladorLogin {

    @Autowired
    private ServicioUsuario servicio;

    @ModelAttribute("Usuario")
    public RegistroUsuarioDto registroUsuarioDto(){

        return new RegistroUsuarioDto();
    }

    @GetMapping
    public String showLogin(String userName, String password, Model model) {
        return "login";
    }

    @PostMapping
    public String loggear(@ModelAttribute("Usuario")RegistroUsuarioDto registroDto){
        String realPassword =  servicio.userPassword(registroDto.getUserName());
        
        if(realPassword != null)
        {
            if(realPassword.equals(registroDto.getPassword())) 
            {
                return "index";
            }

            return "login?failure"; // No existe el usuario
        }
        
        return "login?failure"; // No coinciden el usuario y la contraseña
    }

}
