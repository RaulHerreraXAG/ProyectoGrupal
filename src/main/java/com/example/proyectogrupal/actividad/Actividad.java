package com.example.proyectogrupal.actividad;


import com.example.proyectogrupal.alumno.Alumno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "actividad")
public class Actividad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Actividad;

    @Column(name = "Fecha")
    private LocalDate fecha;

    @Column(name = "Tipo")
    @Enumerated(EnumType.STRING)
    private Tips tipo;

    @Column(name = "Horas")
    private Integer horas;

    @Column(name = "Actividad")
    private String actividad;

    @Column(name = "Observacion")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "Alumno", referencedColumnName = "ID_Alumno")
    private Alumno alumno;


    @Override
    public String toString() {
        return "Actividad{" +
                "ID_Actividad=" + ID_Actividad +
                ", fecha=" + fecha +
                ", tipo=" + tipo +
                ", horas=" + horas +
                ", actividad='" + actividad + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", alumno=" + alumno.getID_Alumno() +
                '}';
    }

    public static void merge(Actividad origen, Actividad destino) {
        destino.setActividad(origen.getActividad());
        destino.setTipo(origen.getTipo());
        destino.setAlumno(origen.getAlumno());
        destino.setHoras(origen.getHoras());
        destino.setFecha(origen.getFecha());
        destino.setObservaciones(origen.getObservaciones());
    }
}
