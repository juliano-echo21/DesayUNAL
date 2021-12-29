package com.example.desayunal.services;

import com.example.desayunal.repository.RepositorioUsuario;

public class ServicioUsuarioImpl implements  ServicioUsuario{

    private RepositorioUsuario repositorioUsuario;


    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario ){
        super();
        this.repositorioUsuario = repositorioUsuario;
    }


}
