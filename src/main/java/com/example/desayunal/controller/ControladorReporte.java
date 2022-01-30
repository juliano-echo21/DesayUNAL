package com.example.desayunal.controller;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;

import com.example.desayunal.services.ServicioProducto;
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
        Iterator<Integer> ordenesIterator;
        List<Integer> ordenes = servicioO.idsOrdenesPorMes(mes);
        List<Integer> productosId = new LinkedList<Integer>();
        int productos = servicioP.totalProductos();
        int ventasTmp;
        int ventasT = 0;

        for(int productId = 1; productId <= productos; ++productId){
            ordenesIterator = ordenes.iterator();
            ventasTmp = 0;

            while(ordenesIterator.hasNext()){
                ventasTmp += servicioO.ventasProductoPorOrden(productId, ordenesIterator.next());
            }

            if(ventasTmp > ventasT){
                productosId.clear();
                productosId.add(productId);

                ventasT = 0;
            }
            else if(ventasTmp == ventasT){
                productosId.add(productId);
            }
        }

        return productosId;
    }
}
