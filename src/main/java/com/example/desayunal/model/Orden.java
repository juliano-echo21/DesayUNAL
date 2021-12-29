package com.example.desayunal.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table( )
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha")
    @NotNull
    private String fecha;

    @Column(name = "horaPedido")
    @NotNull
    private String horaPedido;

    @Column(name = "horaEntrega")
    @NotNull
    private String horaEntrega;

    @Column(name = "precio")
    @NotNull
    private int precio;

    @Column(name = "estado")
    @NotNull
    private String estado;

    @JoinColumn(name = "fk_usuarioID", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Usuario usuario;

    @JoinColumn(name = "fk_domiciliarioID", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Domiciliario domiciliario;
}
