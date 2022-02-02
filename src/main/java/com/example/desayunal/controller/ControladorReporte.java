package com.example.desayunal.controller;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.example.desayunal.services.ServicioProducto;
import com.example.desayunal.model.DetallesOrden;
import com.example.desayunal.model.Orden;
import com.example.desayunal.model.Producto;
import com.example.desayunal.services.ServicioOrden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reporte")
public class ControladorReporte {
    @Autowired
    private ServicioOrden servicioO;

    @Autowired
    private ServicioProducto servicioP;

    public List<Producto> productosMasVendidosMes(int mes){
        Iterator<DetallesOrden> detallesOrdenIterator;
        List<Orden> ordenes = servicioO.idsOrdenesPorMes(mes);
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

        while(iterator.hasNext()){
            producto = iteratorP.next();
            ventaP = productosTotal.get(producto);

            if(ventaP > ventasT){
                productosMasVendidos.clear();
                productosMasVendidos.add(producto);

                ventasT = ventaP;
            }
            else if(ventaP == ventasT){
                productosMasVendidos.add(producto);
            }
        }

        return productosMasVendidos;
    }
}
