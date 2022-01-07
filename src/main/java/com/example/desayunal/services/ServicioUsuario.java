package com.example.desayunal.services;

import java.util.List;
import com.example.desayunal.InterfacesServicios.IServicioUsuario;
import com.example.desayunal.model.Usuario;
import com.example.desayunal.repository.RepositorioUsuario;
import com.example.desayunal.web.dto.RegistroUsuarioDto;

import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario implements IServicioUsuario {

    private RepositorioUsuario repositorioUsuario;

    public ServicioUsuario(RepositorioUsuario repositorioUsuario ){
        super();
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public Usuario save(RegistroUsuarioDto registroDto)
    {
        Usuario usuario = new Usuario
                              (
                                registroDto.getUserName(), 
                                registroDto.getPassword(), 
                                registroDto.getEstado(), 
                                registroDto.getRole()
                              );
        
        return repositorioUsuario.save(usuario);
    }

    @Override
    public String userPassword(String userName)
    {
        return repositorioUsuario.findByUserName(userName);
    }


}
