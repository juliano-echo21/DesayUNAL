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
}
