package com.example.proyectogrupal.empresa;

import com.example.proyectogrupal.alumno.Alumno;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una Empresa con sus atributos y relaciones.
 */
@Data
@Entity
@Table(name = "empresas")
public class Empresa implements Serializable {

    /**
     * Identificador de la empresa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Empresa;

    /**
     * Nombre de la empresa.
     */
    @Column(name = "Nombre")
    private String nombre;

    /**
     * Email de la empresa.
     */
    @Column(name = "Email")
    private String email;

    /**
     * Telefono de la empresa.
     */
    @Column(name = "Telefono")
    private Integer telefono;

    /**
     * Responsable de la empresa.
     */
    @Column(name = "Responsable")
    private String responsable;

    /**
     * Observaciones de la empresa.
     */
    @Column(name = "Observaciones")
    private String observaciones;

    /**
     * Lista de alumnos que tiene la empresa.
     */
    @OneToMany(mappedBy = "empresa")
    private List<Alumno> alumnos = new ArrayList<>();


    /**
     * @return Devuelve una cadena con la información de la empresa.
     */
    @Override
    public String toString() {
        return "Empresa{" +
                "ID_Empresa=" + ID_Empresa +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                ", responsable='" + responsable + '\'' +
                ", observaciones='" + observaciones + '\'' +
                //", alumnos=" + alumnos +
                '}';
    }

    /**
     * Actualiza el objeto destino con los valores del objeto origen.
     *
     * @param origen  La empresa con los datos nuevos.
     * @param destino La empresa que se actualiza.
     */
    public static void merge(Empresa origen, Empresa destino) {
        destino.setNombre(origen.getNombre());
        destino.setEmail(origen.getEmail());
        destino.setTelefono(origen.getTelefono());
        destino.setResponsable(origen.getResponsable());
        destino.setObservaciones(origen.getObservaciones());
    }
}
