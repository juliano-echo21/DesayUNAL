package com.example.desayunal.controller;

import java.util.Stack;

import com.example.desayunal.model.Carrito;
import com.example.desayunal.model.Producto;
import com.example.desayunal.services.ServicioProducto;
import com.example.desayunal.services.ServicioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ControladorCarrito {
    @Autowired
    private ServicioProducto sProducto;
    
    @Autowired 
    private ServicioUsuario sUsuario;

    private Stack<Carrito> listaCarrito = new Stack<>();
    private int item;
    private int totalPagar = 0;
    private int cantidad = 1;
    public static int cantidadCarrito = 0;
    
    @GetMapping("/agregarCarrito/{id}")
    public String agregarCarrito( @PathVariable int id, Model model){
 

        Producto p = sProducto.listarId(id).get();

        Carrito car = new Carrito();
        car.setId(++item);
        car.setIdProducto(id);
        car.setNombre(p.getNombre());
        car.setDescripcion(p.getDescripcion());
        car.setPrecioCompra(p.getPrecio());
        car.setCantidad(cantidad);
        car.setSubTotal(cantidad*p.getPrecio());

        listaCarrito.add(car);
        
        cantidadCarrito = listaCarrito.size();
       /* for(int i = 0 ; i < 10; i++)
            System.out.println(p.getNombre());*/
        System.out.println(id);
    
        return "redirect:../desayunal?agregado";
    }

    @GetMapping("/carrito")
    public String carrito(Model model){
        totalPagar = 0;
        for (Carrito c : listaCarrito){
            totalPagar += c.getSubTotal();
        }
        model.addAttribute("carrito", listaCarrito);
        model.addAttribute("totalPagar",totalPagar);
        model = sUsuario.actualizarEstados(model);
        return "carrito";   
    }
}
