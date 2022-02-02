package com.example.desayunal.controller;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.example.desayunal.services.ServicioProducto;
import com.example.desayunal.services.ServicioUsuario;
import com.example.desayunal.model.DetallesOrden;
import com.example.desayunal.model.Orden;
import com.example.desayunal.model.Producto;
import com.example.desayunal.services.ServicioOrden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorReporte {
    @Autowired
    private ServicioOrden servicioO;

    @Autowired
    private ServicioUsuario sUsuario;

    @Autowired
    private ServicioProducto servicioP;

    private int mayorVentaMes;
    private int mes;
    private int dia;

    @GetMapping("/reporte")
    public String reporte(Model model){
        if(!sUsuario.getEstadoLogin()){
            return "redirect:desayunal";
        }

        mes = obtenerMes();
        dia = obtenerDia();

        List<Producto> productos = productosMasVendidosMes();
        model.addAttribute("masVendidos", productos);
        model.addAttribute("mayorVentas", mayorVentaMes);

        //Las siguientes 2 lineas se utilizan para que se muestre correctamente la info en la barra de navegación
        model.addAttribute("page", "admin");
        model = sUsuario.actualizarEstados(model);
        
       /* while(productos.hasNext()){
            System.out.println(productos.next().getNombre());
        }*/

        return "reporte";
    }

    public int obtenerMes(){
        LocalDateTime fechaActual=LocalDateTime.now();
        int mes=fechaActual.getMonthValue();
        return mes;
    }

    public int obtenerDia(){
        LocalDateTime fechaActual = LocalDateTime.now();
        int dia = fechaActual.getDayOfMonth();

        return dia;
    }

    public int ingresosTotalesMes(){
        int ingresosTotales = 0;
        Iterator<Orden> ordenesIterator = servicioO.idsOrdenesPorMes(mes).iterator();

        while(ordenesIterator.hasNext()){
            ingresosTotales += ordenesIterator.next().getPrecio(); 
        }

        return ingresosTotales;
    }

    public int ingresosTotalesDia(){
        int ingresosTotales = 0;
        Iterator<Orden> ordenesIterator = servicioO.idsOrdenesPorFecha(dia, mes, 2022).iterator();

        while(ordenesIterator.hasNext()){
            ingresosTotales += ordenesIterator.next().getPrecio();
        }

        return ingresosTotales;
    }


    public List<Producto> productosMasVendidosMes(){
        Iterator<DetallesOrden> detallesOrdenIterator;
        List<Orden> ordenes = servicioO.idsOrdenesPorMes(mes);
        //System.out.println(ordenes.size());
        HashMap<Producto, Integer> productosTotal = new HashMap<Producto, Integer>();
        List<Producto> productosMasVendidos = new LinkedList<Producto>();

        Iterator<Orden> iterator = ordenes.iterator();
        Iterator<Producto> iteratorP;


        DetallesOrden detallesOrden;
        
        Producto producto;
        int cantidadProducto;
        Orden orden;
        
        int ventasT = 0;
        int ventaP;


        while(iterator.hasNext()){
            orden = iterator.next();

            detallesOrdenIterator = servicioO.detallesOrden(orden).iterator();

            while(detallesOrdenIterator.hasNext()){
                detallesOrden = detallesOrdenIterator.next();
                producto = detallesOrden.getProductoID();

                cantidadProducto = detallesOrden.getCantidadProducto();

                if(productosTotal.containsKey(producto)){
                    productosTotal.replace(producto, productosTotal.get(producto) + cantidadProducto);
                }
                else{
                    productosTotal.put(producto, cantidadProducto);
                }
            }   
        }

        iteratorP = productosTotal.keySet().iterator();

        while(iteratorP.hasNext()){
            producto = iteratorP.next();

            ventaP = productosTotal.get(producto);

            if(ventaP > ventasT){
                productosMasVendidos.clear();
                productosMasVendidos.add(producto);
                ventasT = ventaP;
                mayorVentaMes=ventaP;
            }
            else if(ventaP == ventasT){
                productosMasVendidos.add(producto);
            }
        }

        return productosMasVendidos;
    }
}
