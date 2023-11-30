package com.example.proyectogrupal;

import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.empresa.Empresa;
import com.example.proyectogrupal.profesor.Profesor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Session {
    @Getter
    @Setter
    private static Integer pos = null;

    @Getter
    @Setter
    private static Profesor profesor;

    @Getter
    @Setter
    private static Empresa empresa;

    @Getter
    @Setter
    private static Alumno alumno;

    @Getter
    @Setter
    private static Actividad actividad;




}
