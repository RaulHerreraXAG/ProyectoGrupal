package com.example.proyectogrupal.actividad;


import com.example.proyectogrupal.alumno.Alumno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * La clase Actividad representa una actividad realizada por un alumno.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "actividad")
public class Actividad implements Serializable {
    /**
     * Identificador para la actividad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Actividad;

    /**
     * Fecha en la que se realizó la actividad.
     */
    @Column(name = "Fecha")
    private LocalDate fecha;

    /**
     * Tipo de actividad.
     */
    @Column(name = "Tipo")
    @Enumerated(EnumType.STRING)
    private Tips tipo;

    /**
     * Número de horas dedicadas en la actividad.
     */
    @Column(name = "Horas")
    private Integer horas;

    /**
     * Descripción de la activad.
     */
    @Column(name = "Actividad")
    private String actividad;

    /**
     * Observaciones o incidencias adicionales relacionadas con la actividad.
     */
    @Column(name = "Observacion")
    private String observaciones;

    /**
     * Alumno asociado a esta actividad.
     */
    @ManyToOne
    @JoinColumn(name = "Alumno", referencedColumnName = "ID_Alumno")
    private Alumno alumno;


    /**
     * @return Devuelve una cadena con la información de la actividad.
     */
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

    /**
     * Actualiza el objeto destino con los valores del objeto origen.
     *
     * @param origen  La actividad con los datos nuevos.
     * @param destino La actividad que se actualiza.
     */
    public static void merge(Actividad origen, Actividad destino) {
        destino.setActividad(origen.getActividad());
        destino.setTipo(origen.getTipo());
        destino.setAlumno(origen.getAlumno());
        destino.setHoras(origen.getHoras());
        destino.setFecha(origen.getFecha());
        destino.setObservaciones(origen.getObservaciones());
    }
}
