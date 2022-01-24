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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        int pos = 0;
        cantidad = 1;
        Producto p = sProducto.listarId(id).get();

        if(!listaCarrito.empty()){
            for(int i = 0; i < listaCarrito.size(); i++){
                if(id == listaCarrito.get(i).getIdProducto()){
                    pos = i;
                }
            }
            if(id == listaCarrito.get(pos).getIdProducto()){
                cantidad += listaCarrito.get(pos).getCantidad();
                int subTotal = listaCarrito.get(pos).getPrecioCompra()*cantidad;
                listaCarrito.get(pos).setCantidad(cantidad);
                listaCarrito.get(pos).setSubTotal(subTotal);
            }else{
                Carrito car = new Carrito();
                car.setId(++item);
                car.setIdProducto(id);
                car.setNombre(p.getNombre());
                car.setDescripcion(p.getDescripcion());
                car.setPrecioCompra(p.getPrecio());
                car.setCantidad(cantidad);
                car.setSubTotal(cantidad*p.getPrecio());
                listaCarrito.add(car);
            }
        }else{
            Carrito car = new Carrito();
            car.setId(++item);
            car.setIdProducto(id);
            car.setNombre(p.getNombre());
            car.setDescripcion(p.getDescripcion());
            car.setPrecioCompra(p.getPrecio());
            car.setCantidad(cantidad);
            car.setSubTotal(cantidad*p.getPrecio());
            listaCarrito.add(car);
        }
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

    @PostMapping("/eliminarDelCarrito")
    public void eliminarDelCarrito(@RequestParam int idp){
        for(int i = 0; i < listaCarrito.size(); i++){
            if(listaCarrito.get(i).getId() == idp)
                listaCarrito.remove(i);
        }
        cantidadCarrito = listaCarrito.size();
    }

    @PostMapping("/actualizarCarrito")
    public void actualizarCarrito(@RequestParam int idp, @RequestParam int cantidad){
        for(int i = 0; i < listaCarrito.size(); i++){
            if(listaCarrito.get(i).getId() == idp){
                listaCarrito.get(i).setCantidad(cantidad);
                int subTotal = listaCarrito.get(i).getPrecioCompra()*cantidad;
                listaCarrito.get(i).setSubTotal(subTotal);
            }
        }
    }

    
}
