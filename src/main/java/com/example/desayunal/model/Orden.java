package com.example.desayunal.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table( )
public class Orden {

    public Orden(){}

    public Orden(short dia, short mes, short año, String horaPedido, String horaEntrega, int precio, String estado, Usuario usuario) {
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.horaPedido = horaPedido;
        this.horaEntrega = horaEntrega;
        this.precio = precio;
        this.estado = estado;
        this.usuario = usuario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dia", nullable = false)
    @NotNull
    private short dia;

    @Column(name = "mes", nullable = false)
    @NotNull
    private short mes;

    @Column(name = "año", nullable = false)
    @NotNull
    private short año;

    @Column(name = "horaPedido", nullable = false)
    @NotNull
    private String horaPedido;

    @Column(name = "horaEntrega", nullable = false)
    @NotNull
    private String horaEntrega;

    @Column(name = "precio", nullable = false)
    @NotNull
    private int precio;

    @Column(name = "estado", nullable = false)
    @NotNull
    private String estado;

    @JoinColumn(name = "fk_usuarioID", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Usuario usuario;

   


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraPedido() {
        return this.horaPedido;
    }

    public void setHoraPedido(String horaPedido) {
        this.horaPedido = horaPedido;
    }

    public String getHoraEntrega() {
        return this.horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public int getPrecio() {
        return this.precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

  

}
