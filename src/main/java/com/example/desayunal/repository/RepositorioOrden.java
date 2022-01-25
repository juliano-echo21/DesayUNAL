package com.example.desayunal.repository;

import com.example.desayunal.model.Orden;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioOrden extends CrudRepository<Orden,Integer>{
    
}
