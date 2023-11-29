module com.example.proyectogrupal {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;

    opens com.example.proyectogrupal.actividad;
    opens com.example.proyectogrupal.alumno;
    opens com.example.proyectogrupal.empresa;



    opens com.example.proyectogrupal to javafx.fxml;
    opens com.example.proyectogrupal.controllers to javafx.fxml;


    exports com.example.proyectogrupal;
    exports com.example.proyectogrupal.controllers;
}