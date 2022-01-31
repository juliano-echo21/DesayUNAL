package com.example.desayunal.controller;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.example.desayunal.services.ServicioProducto;
import com.example.desayunal.model.DetallesOrden;
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

    public List<Integer> productosMasVendidosMes(int mes){
        Iterator<DetallesOrden> detallesOrdenIterator;
        List<Integer> ordenes = servicioO.idsOrdenesPorMes(mes);
        HashMap<Integer, Integer> productosTotal = new HashMap<Integer, Integer>();
        List<Integer> productosMasVendidosId = new LinkedList<Integer>();

        Iterator<Integer> iterator = ordenes.iterator();


        DetallesOrden detallesOrden;
        
        int idProducto;
        int cantidadProducto;
        int idOrden;
        
        int ventasT = 0;
        int ventaP;

        while(iterator.hasNext()){
            idOrden = iterator.next();

            detallesOrdenIterator = servicioO.detallesOrden(idOrden).iterator();

            while(detallesOrdenIterator.hasNext()){
                detallesOrden = detallesOrdenIterator.next();
                idProducto = detallesOrden.getProductoID().getId();
                cantidadProducto = detallesOrden.getCantidadProducto();

                if(productosTotal.containsKey(idProducto)){
                    productosTotal.replace(idProducto, productosTotal.get(idProducto) + cantidadProducto);
                }
                else{
                    productosTotal.put(idProducto, cantidadProducto);
                }
            }   
        }

        iterator = productosTotal.keySet().iterator();

        while(iterator.hasNext()){
            idProducto = iterator.next();
            ventaP = productosTotal.get(idProducto);

            if(ventaP > ventasT){
                productosMasVendidosId.clear();
                productosMasVendidosId.add(idProducto);

                ventasT = ventaP;
            }
            else if(ventaP == ventasT){
                productosMasVendidosId.add(idProducto);
            }
        }

        return productosMasVendidosId;
    }
}
