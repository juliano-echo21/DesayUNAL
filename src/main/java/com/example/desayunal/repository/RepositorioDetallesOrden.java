package com.example.desayunal.repository;

import java.util.List;

import com.example.desayunal.model.DetallesOrden;
import com.example.desayunal.model.Orden;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioDetallesOrden extends CrudRepository<DetallesOrden,Integer>{
    public List<DetallesOrden> findByOrdenID(Orden usuario);

    @Query(value = "SELECT cantidad_producto FROM detalles_orden WHERE fk_ordenid = ?2 AND fk_productoid = ?1")
    public int timesSoldProductByOrdenId(int idProducto, int idOrden);
}
