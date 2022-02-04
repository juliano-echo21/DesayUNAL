package com.example.desayunal.controller;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.example.desayunal.services.ServicioProducto;
import com.example.desayunal.services.ServicioUsuario;
import com.example.desayunal.model.DetallesOrden;
import com.example.desayunal.model.Orden;
import com.example.desayunal.model.Producto;
import com.example.desayunal.model.Usuario;
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
        if(!sUsuario.getEstadoLogin() || !sUsuario.getUsuarioConectado().getRole().equals("Administrador")){
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

    /**
     * Esta función permite proyectar las ventas del próximo mes
     * @param arregloMeses: arreglo con las ventas de los meses anteriores (se recomienda máximo 3)
     * @return double con el valor de las ventas proyectadas para el próximo mes
     */
    private double proyectar(ArrayList<Double> arregloMeses){
        int n = arregloMeses.size();
        ArrayList<Double> x = new ArrayList<>();
        for(double i = 0; i<n; i++){
            x.add(i);
        }
        double result = 0;
       
        
        
        for(int i = 0; i< n; i++){
            double valor = 1;

            for(int j = 0; j< n; j++){
                if(j!=i){
                    valor = valor * (n - x.get(j))/(x.get(i)-x.get(j));
                }
            }
            result = result + arregloMeses.get(i)*valor;
        }
        return result;
    }
}
