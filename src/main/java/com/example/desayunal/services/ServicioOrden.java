package com.example.desayunal.services;

import com.example.desayunal.interfacesServicios.IServicioOrden;
import com.example.desayunal.model.DetallesOrden;
import com.example.desayunal.model.Orden;
import com.example.desayunal.repository.RepositorioDetallesOrden;
import com.example.desayunal.repository.RepositorioOrden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioOrden implements IServicioOrden {
    @Autowired
    private RepositorioOrden data;

    @Autowired
    private RepositorioDetallesOrden repDetalles;

    @Override
    public int guardar(Orden o) {
        int res = 0;
        Orden orden = data.save(o);
        if(!orden.equals(null)){
            res = 1;
        }
        return res;
    }

    @Override
    public int guardarDetalles(DetallesOrden dO){
        int res = 0;
        DetallesOrden dOrden = repDetalles.save(dO);
        if(!dOrden.equals(null)){
            res = 1;
        }
        return res;
    }
}
