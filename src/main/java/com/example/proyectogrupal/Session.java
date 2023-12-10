package com.example.proyectogrupal;

import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.empresa.Empresa;
import com.example.proyectogrupal.profesor.Profesor;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase de gestión de la sesión actual.
 */
public class Session {

    /**
     * Establece y obtiene el alumno actual en sesión.
     */
    @Getter
    @Setter
    private static Alumno currentAlumno;
    /**
     * Establece y obtiene el profesor actual en sesión.
     */
    @Getter
    @Setter
    private static Profesor currentProfesor;
    /**
     * Establece y obtiene la empresa actual en sesión.
     */
    @Getter
    @Setter
    private static Empresa currentEmpresa;
    /**
     * Establece y obtiene la actividad actual en sesión.
     */
    @Getter
    @Setter
    private static Actividad currentActividad;


}
