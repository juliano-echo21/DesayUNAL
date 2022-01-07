package com.example.desayunal.controller;

import java.util.List;
import com.example.desayunal.model.Usuario;
import com.example.desayunal.services.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ControladorRegistro {
    @Autowired
    private ServicioUsuario servicio;

    /*@GetMapping("/login")
    public String loggear(String userName, String password, Model model)
    {   
        Usuario usuario = servicio.userExists(userName).get(0);

        if(usuario == null)
        {
            //f
        }
        else
        {
            if(!usuario.getPassword().equals(password))
            {
                //f
            }
        }
        
        return "catalogo";
    }*/

}
