package com.example.proyectogrupal.actividad;


import com.example.proyectogrupal.alumno.Alumno;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "actividad")

public class Actividad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Fecha")
    private Date fecha;

    @Column(name = "Tipo")
    @Enumerated(EnumType.STRING)
    private Tips tipo;

    @Column(name = "Horas")
    private Integer horas;

    @Column(name = "Actividad")
    private String actividad;

    @Column(name = "Observaciones")
    private String observaciones;

  @ManyToOne
    @JoinColumn(name = "ID" )
    private Alumno alumno;



}
