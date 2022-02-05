package com.example.desayunal.controller;



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

        //int [] ventas20 = ventasAño(2020);
        int [] ventas21 = ventasAño(2021);
        //int [] ventas22 = ventasAño(2022);

        // Promedio producto por compra
        int numOrdenes = servicioOrden.numOrdenes();
        int  cantProductosVendidos = servicioOrden.cantProductosVendidos();

        double ppp = cantProductosVendidos/numOrdenes;
        System.out.println("Promedio producto por compra " + ppp);

        //Tendencias hora de pedido por franjas

        
        return  "analisis";
    }


    public int[] ventasAño(int anio){
        int [] ventas = new int[12]; 

        for(int i=0;i<12;i++){
        
            ventas[i] = servicioOrden.ventasMes(anio, i+1);
            System.out.println("las ventas son " + ventas[i]);
            
        }
        return ventas;
    }
    
}

