package com.example.desayunal.repository;

import java.util.List;

import com.example.desayunal.model.Orden;
import com.example.desayunal.model.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioOrden extends CrudRepository<Orden,Integer>{
    @Query(value = "SELECT p FROM Orden p WHERE p.usuario = ?1")
    public List<Orden> findByUsuario(Usuario usuario);

    @Query(value = "SELECT p FROM Orden p WHERE p.mes = ?1")
    public List<Orden> idsByMes(int mes);
    
    @Query(value = "SELECT COUNT(id), fk_usuarioid FROM orden GROUP BY fk_usuarioid ORDER BY COUNT(id) DESC LIMIT 0, 5;", nativeQuery = true)
    public List<Integer[]>  usuariosMasFrecuentes();
    
    @Query(value = "SELECT p FROM Orden p WHERE p.dia = ?1 AND p.mes = ?2 AND p.anio = ?3")
    public List<Orden> idsByFecha(int dia, int mes, int año);
    
    // Suma de ventas en un mes de un año
    @Query(value = "SELECT SUM(precio) FROM orden WHERE anio = ?1 AND mes = ?2", nativeQuery = true)
    public int ventasMes(int anio, int mes);

}
