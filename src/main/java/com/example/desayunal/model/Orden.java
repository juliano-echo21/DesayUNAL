package com.example.desayunal.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table( )
public class Orden {

    public Orden(String fecha, String horaPedido, String horaEntrega, int precio, String estado, Usuario usuario, Domiciliario domiciliario) {
        this.fecha = fecha;
        this.horaPedido = horaPedido;
        this.horaEntrega = horaEntrega;
        this.precio = precio;
        this.estado = estado;
        this.usuario = usuario;
        this.domiciliario = domiciliario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha", nullable = false)
    @NotNull
    private String fecha;

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

    @JoinColumn(name = "fk_domiciliarioID", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Domiciliario domiciliario;
}
