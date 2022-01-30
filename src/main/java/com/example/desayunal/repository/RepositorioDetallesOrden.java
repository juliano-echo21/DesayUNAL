package com.example.desayunal.repository;

import java.util.List;

import com.example.desayunal.model.DetallesOrden;
import com.example.desayunal.model.Orden;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioDetallesOrden extends CrudRepository<DetallesOrden,Integer>{
    public List<DetallesOrden> findByOrdenID(Orden usuario);

    @Query(value = "SELECT SUM(p.cantidad_producto) FROM (SELECT p FROM detalles_orden WHERE p.fk_ordenid = ?2 AND p.fk_productoid = ?1)")
    public int timesSoldProductByOrdenId(int idProducto, int idOrden);
}
