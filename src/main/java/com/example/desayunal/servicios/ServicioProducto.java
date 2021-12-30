package com.example.desayunal.servicios;

import com.example.desayunal.interfacesServicios.IServicioProducto;
import com.example.desayunal.repository.RepositorioProducto;
import com.example.desayunal.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioProducto implements IServicioProducto {
    @Autowired
    private RepositorioProducto data;

    @Override
    public List<Producto> listar() {
        return (List<Producto>) data.findAll();
    }

    @Override
    public Optional<Producto> listarId(int id) {
        return data.findById(id);
    }

    @Override
    public int guardar(Producto p) {
        int res = 0;
        Producto producto = data.save(p);
        if(!producto.equals(null)){
            res = 1;
        }
        return 0;
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
