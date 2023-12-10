package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.actividad.ActividadDAO;
import com.example.proyectogrupal.actividad.Tips;
import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.alumno.AlumnoDAO;
import com.example.proyectogrupal.empresa.Empresa;
import com.example.proyectogrupal.empresa.EmpresaDAO;
import javafx.beans.Observable;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Esta clase controla la interfaz de usuario de la vista del alumno en la aplicación.
 * Muestra información del alumno, incluyendo actividades realizadas, detalles personales y más.
 * Permite añadir nuevas actividades y cerrar sesión.
 */

public class InterfazAlumno implements Initializable {
    @javafx.fxml.FXML
    private Button btnCerrar;
    @javafx.fxml.FXML
    private Button btnAñadir;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cFecha;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cTipoPractica;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cHorasDia;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cActividadRealizada;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cObservaciones;
    @javafx.fxml.FXML
    private TableView TvActividades;
    @javafx.fxml.FXML
    private TextField textDNI;
    @javafx.fxml.FXML
    private TextField textEmpresa;
    @javafx.fxml.FXML
    private TextField textFechaNac;
    @javafx.fxml.FXML
    private TextField textProfesor;
    @javafx.fxml.FXML
    private TextField textEmail;
    @javafx.fxml.FXML
    private Label labelNombreAlumno;
    @javafx.fxml.FXML
    private TextField textTelefono;
    @javafx.fxml.FXML
    private TextField textHorasRealizadas;
    @javafx.fxml.FXML
    private TextField textHorasRestantes;
    @javafx.fxml.FXML
    private TextField textNombreEmpresa;
    @javafx.fxml.FXML
    private TextField textResponsableEmpresa;
    @javafx.fxml.FXML
    private TextField textEmailEmpresa;
    @javafx.fxml.FXML
    private TextField textTelefonoEmpresa;
    private ObservableList<Actividad> observableList;

    private ActividadDAO actividadDAO = new ActividadDAO();

    /**
     * Inicializa la interfaz de usuario con datos del alumno y configuración de la tabla de actividades.
     * @param url La URL base.
     * @param resourceBundle El recurso bundle.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Mostramos los datos en las columnas.
        cFecha.setCellValueFactory((fila) -> {
            LocalDate fecha = fila.getValue().getFecha();
            String fechaFormato = "";

            if (fecha != null) {
                DateTimeFormatter nuevoFormato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                fechaFormato = fecha.format(nuevoFormato);
            }

            return new SimpleStringProperty(fechaFormato);
        });

        cTipoPractica.setCellValueFactory((fila) -> {
            String tipo = String.valueOf(fila.getValue().getTipo());
            return new SimpleStringProperty(tipo);
        });

        cHorasDia.setCellValueFactory((fila) -> {
            String horas = String.valueOf(fila.getValue().getHoras());
            return new SimpleStringProperty(horas);
        });

        cActividadRealizada.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getActividad());
        });

        cObservaciones.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getObservaciones());
        });

        //cargamos los datos en la tabla
        Alumno currentAlumno = Session.getCurrentAlumno();

        if (currentAlumno != null) {
            if (currentAlumno.getActividad_diaria() != null) {
                observableList = FXCollections.observableArrayList(currentAlumno.getActividad_diaria());
            } else {
                observableList = FXCollections.observableArrayList();
            }

            TvActividades.setItems(observableList);
        }

        labelNombreAlumno.setText("Bienvenido/a " + Session.getCurrentAlumno().getNombre());
        textDNI.setText(Session.getCurrentAlumno().getDNI());
        textEmail.setText(Session.getCurrentAlumno().getEmail());
        Empresa empresa = currentAlumno.getEmpresa();
        if (empresa != null) {
            // La empresa no es null, puedes acceder al nombre de la empresa
            textEmpresa.setText(String.valueOf(empresa.getNombre()));
            textNombreEmpresa.setText(Session.getCurrentAlumno().getEmpresa().getNombre());
            textResponsableEmpresa.setText(Session.getCurrentAlumno().getEmpresa().getResponsable());
            textEmailEmpresa.setText(Session.getCurrentAlumno().getEmpresa().getEmail());
            textTelefonoEmpresa.setText(String.valueOf(Session.getCurrentAlumno().getEmpresa().getTelefono()));
             }
        textProfesor.setText(String.valueOf(Session.getCurrentAlumno().getTutor().getNombre()));
        textTelefono.setText(String.valueOf(Session.getCurrentAlumno().getTelefono()));
        textFechaNac.setText(String.valueOf(Session.getCurrentAlumno().getNacimiento()));

        EmpresaDAO empresaDAO = new EmpresaDAO();

        ActividadDAO actividadDAO=new ActividadDAO();
        List<Actividad> actividades = Session.getCurrentAlumno().getActividad_diaria();
        int horasTotales = actividadDAO.calcularTotalHoras(actividades);
        textHorasRealizadas.setText(String.valueOf(horasTotales));

        int horasRestantes= (700-horasTotales);
        textHorasRestantes.setText(String.valueOf(horasRestantes));


        TvActividades.setOnMouseClicked(event -> {
            if(event.getClickCount()==1){
                Actividad actividad = (Actividad) TvActividades.getSelectionModel().getSelectedItem();
                if (actividad != null){
                    Session.setCurrentActividad(actividad);
                    try {
                        App.changeScene("AlumEditActi.fxml", "Editar actividad");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }

    /**
     * Maneja el evento de añadir una nueva actividad.
     * Cambia la escena a la página de añadir actividad para el alumno.
     * @param actionEvent El evento de acción.
     */
    @javafx.fxml.FXML
    public void añadirActividad(ActionEvent actionEvent) {
        // Código para cambiar a la escena de añadir actividad...
        try {
            App.changeScene("AlumAñadeActi.fxml", "Página Alumno");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Maneja el evento de cerrar sesión.
     * Cambia la escena al inicio de sesión.
     * @param actionEvent El evento de acción.
     */
    @javafx.fxml.FXML
    public void cerrarsession(ActionEvent actionEvent) {
        // Código para cambiar a la escena de inicio de sesión...
        try {
            App.changeScene("login.fxml","Login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
