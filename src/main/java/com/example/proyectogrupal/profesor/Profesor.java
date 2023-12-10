package com.example.proyectogrupal.profesor;

import com.example.proyectogrupal.alumno.Alumno;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Representa a un profesor de la base de datos.
 */
@Data
@Entity
@Table(name = "profesor")
public class Profesor implements Serializable {
    /**
     * Identificador del profesor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Profesor")
    private Long ID_Profesor;
    /**
     * Nombre del profesor.
     */
    @Column(name = "Nombre")
    private String nombre;

    /**
     * Apellidos del profesor.
     */
    @Column(name = "Apellidos")
    private String apellidos;

    /**
     * Contraseña del profesor.
     */
    @Column(name = "Contrasenya")
    private String contrasenya;

    /**
     * Email del profesor.
     */
    @Column(name = "Email")
    private String email;

    /**
     * Lista de alumnos que tiene un profesor.
     */
    @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
    private List<Alumno> alumnos = new ArrayList<>();

    /**
     * @return Devuelve una cadena con la información del profesor.
     */
    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + ID_Profesor +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                ", email='" + email + '\'' +
                ", alumnos=" + alumnos +
                '}';
    }
}
