package com.example.proyectogrupal;

import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.actividad.ActividadDAO;
import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.empresa.Empresa;
import com.example.proyectogrupal.profesor.Profesor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Session {

    @Getter
    @Setter
    private static Alumno currentAlumno;
    @Getter
    @Setter
    private static Profesor currentProfesor;
    @Getter
    @Setter
    private static Empresa currentEmpresa;
    @Getter
    @Setter
    private static Actividad currentActividad;

}
