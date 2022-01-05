package com.example.desayunal.services;

import com.example.desayunal.InterfacesServicios.IServicioUsuario;
import com.example.desayunal.model.Usuario;
import com.example.desayunal.repository.RepositorioUsuario;
import com.example.desayunal.web.dto.RegistroUsuarioDto;

import org.springframework.stereotype.Service;

@Service
public class ServicioUsuarioImpl implements IServicioUsuario {

    private RepositorioUsuario repositorioUsuario;

    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario ){
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


}
