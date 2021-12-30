package com.example.desayunal.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

public class ComboUsuario {

    public ComboUsuario(Usuario usuarioID, Producto productoID, int cantidadProductoCombo) {
        this.usuarioID = usuarioID;
        this.productoID = productoID;
        this.cantidadProductoCombo = cantidadProductoCombo;
    }

    /*Atributes*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "fk_usuarioID", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Usuario usuarioID;

    @JoinColumn(name = "fk_productoID", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Producto productoID;


    @Column(name = "cantidadProductoCombo")
    @NotNull
    private int cantidadProductoCombo;


    /*getters an setters*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Usuario usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Producto getProductoID() {
        return productoID;
    }

    public void setProductoID(Producto productoID) {
        this.productoID = productoID;
    }

    public int getCantidadProductoCombo() {
        return cantidadProductoCombo;
    }

    public void setCantidadProductoCombo(int cantidadProductoCombo) {
        this.cantidadProductoCombo = cantidadProductoCombo;
    }
}
