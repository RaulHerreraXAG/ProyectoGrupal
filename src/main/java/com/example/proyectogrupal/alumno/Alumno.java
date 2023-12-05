package com.example.proyectogrupal.alumno;

import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.empresa.Empresa;
import com.example.proyectogrupal.profesor.Profesor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alumno")
public class Alumno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Alumno")
    private Long ID_Alumno;

    @Column(name = "DNI")
    private String DNI;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellidos")
    private String apellidos;

    @Column(name = "Contrasenya")
    private String contrasenya;

    @Column(name = "Email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "Empresa",referencedColumnName = "ID_Empresa")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "Tutor",referencedColumnName = "ID_Profesor")
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
                "ID_Alumno=" + ID_Alumno +
                ", DNI='" + DNI + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                ", email='" + email + '\'' +
                ", empresa=" + empresa +
                ", tutor=" + tutor.getID_Profesor() +
                ", observaciones='" + observaciones + '\'' +
                ", nacimiento=" + nacimiento +
                ", horasDual=" + horasDual +
                ", horasFCT=" + horasFCT +
                ", telefono=" + telefono +
               ", actividad_diaria=" + actividad_diaria +
                '}';
    }
}

