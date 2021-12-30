package com.example.desayunal.repository;

import com.example.desayunal.model.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProducto extends CrudRepository<Producto,Integer> {
}
