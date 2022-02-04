package com.example.desayunal.controller;


import java.lang.reflect.Array;
import java.util.Arrays;
import com.example.desayunal.services.ServicioOrden;
import com.example.desayunal.services.ServicioProducto;
import com.example.desayunal.services.ServicioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorAnalisis{
    @Autowired
    private ServicioOrden servicioOrden;

    @Autowired
    private ServicioUsuario servicioUsuario;

    @Autowired
    private ServicioProducto servicioProducto;


    @GetMapping("/analisis")
    public String analisis( Model model){

        int [] ventas20 = ventasAño(2020);
        int [] ventas21 = ventasAño(2021);
        int [] ventas22 = ventasAño(2022);

        
        return  "";
    }


    public int[] ventasAño(int anio){
        int [] ventas = new int[12]; 

        for(int i=1;i<=12;i++){
        
            ventas[i] = servicioOrden.ventasMes(anio, i);

        }
        String str = Arrays.toString(ventas);
        System.out.println(str);
        return ventas;
    }
    
}

