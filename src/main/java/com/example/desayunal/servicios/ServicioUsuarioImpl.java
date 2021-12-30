package com.example.desayunal.servicios;

import com.example.desayunal.interfacesServicios.IServicioUsuario;
import com.example.desayunal.model.Usuario;
import com.example.desayunal.repository.RepositorioUsuario;

public class ServicioUsuarioImpl implements IServicioUsuario {

    private RepositorioUsuario repositorioUsuario;


    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario ){
        super();
        this.repositorioUsuario = repositorioUsuario;
    }
    @Override
    public Usuario save(){
        return null;
    }



}
