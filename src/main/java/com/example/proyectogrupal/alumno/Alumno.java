package com.example.proyectogrupal.alumno;

import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.empresa.Empresa;
import com.example.proyectogrupal.profesor.Profesor;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "DNI")
    private Integer DNI;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellidos")
    private String apellidos;

    @Column(name = "Contraseña")
    private String contrasenya;

    @Column(name = "Email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "Empresa",referencedColumnName = "empresas")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "Tutor",referencedColumnName = "profesor")
    private Profesor tutor;

    @Column(name = "Observaciones")
    private String observaciones;

    @Column(name = "Nacimiento")
    private Date nacimiento;

    @Column(name = "HorasDual")
    private Integer horasDual;

    @Column(name = "HorasFCT")
    private Integer horasFCT;

    @Column(name = "Telefono")
    private Integer telefono;


    @OneToMany(mappedBy = "alumno",fetch = FetchType.EAGER)
    private List<Actividad> actividad_diaria = new ArrayList<>();


    @Override
    public String toString() {
        return "Alumno{" +
                "DNI=" + DNI +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                ", email='" + email + '\'' +
                ", empresa=" + empresa +
                ", tutor=" + tutor +
                ", observaciones='" + observaciones + '\'' +
                ", nacimiento=" + nacimiento +
                ", horasDual=" + horasDual +
                ", horasFCT=" + horasFCT +
                ", telefono=" + telefono +
                ", actividad_diaria=" + actividad_diaria +
                '}';
    }
}
