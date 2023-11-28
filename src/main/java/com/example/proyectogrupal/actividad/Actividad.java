package com.example.proyectogrupal.actividad;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "actividad")

public class Actividad implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;



}
