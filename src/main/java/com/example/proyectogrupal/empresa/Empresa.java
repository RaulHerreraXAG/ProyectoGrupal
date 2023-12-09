package com.example.proyectogrupal.empresa;

import com.example.proyectogrupal.alumno.Alumno;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "empresas")
public class Empresa implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Empresa;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Email")
    private String email;

    @Column(name = "Telefono")
    private Integer telefono;

    @Column(name = "Responsable")
    private String responsable;

    @Column(name = "Observaciones")
    private String observaciones;

   @OneToMany(mappedBy = "empresa")
    private List<Alumno> alumnos = new ArrayList<>();


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
    public static void merge(Empresa origen, Empresa destino) {
        destino.setNombre(origen.getNombre());
        destino.setEmail(origen.getEmail());
        destino.setTelefono(origen.getTelefono());
        destino.setResponsable(origen.getResponsable());
        destino.setObservaciones(origen.getObservaciones());
    }
}
