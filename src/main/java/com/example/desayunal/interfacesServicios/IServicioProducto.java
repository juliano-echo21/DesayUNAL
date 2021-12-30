package com.example.desayunal.interfacesServicios;

import com.example.desayunal.model.Producto;

import java.util.List;
import java.util.Optional;

public interface IServicioProducto {
    public List<Producto> listar();
    public Optional<Producto> listarId(int id);
    public int guardar(Producto p);
    public void eliminar(int id);
}
