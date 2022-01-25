package com.example.desayunal.interfacesServicios;

import com.example.desayunal.model.DetallesOrden;
import com.example.desayunal.model.Orden;

public interface IServicioOrden  {
    public int guardar(Orden o);
    public int guardarDetalles(DetallesOrden dO);
}
