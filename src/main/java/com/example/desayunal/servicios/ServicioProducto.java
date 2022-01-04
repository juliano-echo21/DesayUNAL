package com.example.desayunal.servicios;

import com.example.desayunal.interfacesServicios.IServicioProducto;
import com.example.desayunal.repository.RepositorioProducto;
import com.example.desayunal.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
        return res;
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Producto> listarDisponibles(){
        return (List<Producto>) data.findDisponibles();
    }

    @Override
    public List<Producto> listarCategoria(String categoria) {
       return data.findByCategoria(categoria);
    }

    @Override
    public List<Producto> listarEstado(String estado){
        return data.findByEstado(estado);
    }

    @Override
    public List<Producto> listarCategoriaDisponible(String categoria){
        return (List<Producto>) data.findCategoriaDisponible(categoria);
    }

    
}
