package com.example.desayunal.repository;

import java.util.List;
import com.example.desayunal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> 
{
    @Query(value = "SELECT password FROM Usuario WHERE userName = ?1")
    public List<Usuario> findByUserName(String userName);

}
