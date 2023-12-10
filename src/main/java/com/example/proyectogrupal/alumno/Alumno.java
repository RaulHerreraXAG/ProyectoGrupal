package com.example.proyectogrupal.alumno;

import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.empresa.Empresa;
import com.example.proyectogrupal.profesor.Profesor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un alumno de la base de datos.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alumno")
public class Alumno implements Serializable {
    /**
     * Identificador del alumno.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Alumno")
    private Long ID_Alumno;

    /**
     * DNI del alumno.
     */
    @Column(name = "DNI")
    private String DNI;

    /**
     * Nombre del alumno.
     */
    @Column(name = "Nombre")
    private String nombre;

    /**
     * Apellidos del alumno.
     */
    @Column(name = "Apellidos")
    private String apellidos;

    /**
     * Contraseña del alumno.
     */
    @Column(name = "Contrasenya")
    private String contrasenya;

    /**
     * Email del alumno.
     */
    @Column(name = "Email")
    private String email;

    /**
     * Empresa asignada al alumno.
     */
    @Getter
    @ManyToOne
    @JoinColumn(name = "Empresa", referencedColumnName = "ID_Empresa")
    private Empresa empresa;

    /**
     * Profesor del alumno.
     */
    @ManyToOne
    @JoinColumn(name = "Tutor", referencedColumnName = "ID_Profesor")
    private Profesor tutor;

    /**
     * Observaciones o incidencias adicionales relacionadas con el alumno.
     */
    @Column(name = "Observaciones")
    private String observaciones;

    /**
     * Fecha de nacimiento del alumno.
     */
    @Column(name = "Nacimiento")
    private LocalDate nacimiento;

    /**
     * Horas de prácticas realizadas en la modalidad DUAL.
     */
    @Column(name = "HorasDual")
    private Integer horasDual;

    /**
     * Horas de prácticas realizadas en la FCT.
     */
    @Column(name = "HorasFCT")
    private Integer horasFCT;

    /**
     * Número de télefono del alumno.
     */
    @Column(name = "Telefono")
    private Integer telefono;


    /**
     * Actividades diarias realizadas por el alumno.
     */
    @OneToMany(mappedBy = "alumno", fetch = FetchType.EAGER)
    private List<Actividad> actividad_diaria = new ArrayList<>();

    /**
     * Actualiza el objeto destino con los valores del objeto origen.
     *
     * @param origen  El alumno con los datos nuevos.
     * @param destino El alumno que se actualiza.
     */
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

    /**
     * @return Devuelve una cadena con la información del alumno.
     */
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

