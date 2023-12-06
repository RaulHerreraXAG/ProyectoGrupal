package com.example.proyectogrupal.alumno;

import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.empresa.Empresa;
import com.example.proyectogrupal.profesor.Profesor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private LocalDate nacimiento;

    @Column(name = "HorasDual")
    private Integer horasDual;

    @Column(name = "HorasFCT")
    private Integer horasFCT;

    @Column(name = "Telefono")
    private Integer telefono;


    @OneToMany(mappedBy = "alumno",fetch = FetchType.EAGER)
    private List<Actividad> actividad_diaria = new ArrayList<>();

    public static void merge(Alumno origen, Alumno destino) {
        destino.setNombre(origen.getNombre());
        destino.setApellidos(origen.getApellidos());
        destino.setContrasenya(origen.getContrasenya());
        destino.setDNI(origen.getDNI());
        destino.setNacimiento(origen.getNacimiento());
        destino.setEmail(origen.getEmail());
        destino.setTelefono(origen.getTelefono());
        destino.setEmpresa(origen.getEmpresa());
        destino.setTutor(origen.getTutor());
        destino.setHorasDual(origen.getHorasDual());
        destino.setHorasFCT(origen.getHorasFCT());
        destino.setObservaciones(origen.getObservaciones());
    }

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
                ", tutor=" + tutor.getNombre() +
                ", observaciones='" + observaciones + '\'' +
                ", nacimiento=" + nacimiento +
                ", horasDual=" + horasDual +
                ", horasFCT=" + horasFCT +
                ", telefono=" + telefono +
                ", actividad_diaria=" + actividad_diaria +
                '}';
    }
}

