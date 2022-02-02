package com.example.desayunal.repository;

import java.util.List;

import com.example.desayunal.model.Orden;
import com.example.desayunal.model.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioOrden extends CrudRepository<Orden,Integer>{
    public List<Orden> findByUsuario(Usuario usuario);

    @Query(value = "SELECT p FROM Orden p WHERE p.mes = ?1")
    public List<Orden> idsByMes(int mes);

    @Query(value = "SELECT p FROM Orden p WHERE p.dia = ?1 AND p.mes = ?2 AND p.anio = ?3")
    public List<Orden> idsByFecha(int dia, int mes, int año);
}
