package com.example.desayunal.interfacesServicios;

import java.util.List;

import com.example.desayunal.model.DetallesOrden;
import com.example.desayunal.model.Orden;
import com.example.desayunal.model.Usuario;

public interface IServicioOrden  {
    public int guardar(Orden o);
    public int guardarDetalles(DetallesOrden dO);
    public List<Orden> listarPorUsuario(Usuario usuario);
    public List<DetallesOrden> listarDetalles(Orden orden);
    public List<Orden> idsOrdenesPorMes(int mes);
    public int ventasProductoPorOrden(int idProducto, int idOrden);
    public List<DetallesOrden> detallesOrden(Orden orden);
    public int ventasMes(int anio, int mes);
}
