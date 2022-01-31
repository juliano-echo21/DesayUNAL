package com.example.desayunal.repository;

import java.util.List;

import com.example.desayunal.model.DetallesOrden;
import com.example.desayunal.model.Orden;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioDetallesOrden extends CrudRepository<DetallesOrden,Integer>{
    public List<DetallesOrden> findByOrdenID(Orden usuario);

    @Query(value = "SELECT cantidadProducto FROM DetallesOrden WHERE ordenID = ?2 AND productoID = ?1")
    public int timesSoldProductByOrdenId(int idProducto, int idOrden);

    @Query(value = "SELECT id FROM DetallesOrden WHERE ordenID = ?1")
    public List<DetallesOrden>  productsId(int idOrden);
}
