package com.example.desayunal.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table( )
public class Domiciliario {

    public Domiciliario(String nombre, String password, String estado) {
        super();
        this.nombre = nombre;
        this.password = password;
        this.estado = estado;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    @NotNull
    private String nombre;

    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    @Column(name = "estado", nullable = false)
    @NotNull
    private String estado;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
