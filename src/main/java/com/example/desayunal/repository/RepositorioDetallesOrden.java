package com.example.desayunal.repository;

import java.util.List;

import com.example.desayunal.model.DetallesOrden;
import com.example.desayunal.model.Orden;

import org.springframework.data.repository.CrudRepository;

public interface RepositorioDetallesOrden extends CrudRepository<DetallesOrden,Integer>{
    public List<DetallesOrden> findByOrdenID(Orden usuario);
}
