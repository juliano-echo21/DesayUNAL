package com.example.desayunal.controller;

import com.example.desayunal.services.ServicioUsuario;
import com.example.desayunal.web.dto.RegistroUsuarioDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signUp")

public class ControladorSignUp {
    @Autowired
    private ServicioUsuario servicio;


    @ModelAttribute("Usuario")
    public RegistroUsuarioDto registroUsuarioDto(){

        return new RegistroUsuarioDto();
    }

    @GetMapping
    public String showSignUp(){
        return "signUp";
    }

    @PostMapping
    public String registroCuentaUsusario(@ModelAttribute("Usuario")RegistroUsuarioDto registroDto){
        registroDto.setEstado("Activo");
        registroDto.setRole("Cliente");

        String realPassword =  servicio.userPassword(registroDto.getUserName());
        
        if(realPassword == null)
        {
            servicio.save(registroDto);
            return "SignUp?success";
        }
        
        return "SignUp?failure";
    }
      
}
