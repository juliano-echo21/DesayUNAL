package com.example.desayunal.services;

import com.example.desayunal.InterfaceServices.IProductoService;
import com.example.desayunal.interfaces.IProducto;
import com.example.desayunal.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private IProducto data;

    @Override
    public List<Producto> listar() {
        return (List<Producto>) data.findAll();
    }

    @Override
    public Optional<Producto> listarId(int id) {
        return data.findById(id);
    }

    @Override
    public int save(Producto p) {
        int res = 0;
        Producto producto = data.save(p);
        if(!producto.equals(null)){
            res = 1;
        }
        return 0;
    }

    @Override
    public void delete(int id) {

    }
}
