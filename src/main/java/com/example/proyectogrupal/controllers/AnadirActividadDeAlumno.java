package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.actividad.ActividadDAO;
import com.example.proyectogrupal.actividad.Tips;
import com.example.proyectogrupal.alumno.Alumno;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


/**
 * Controlador para la interfaz de añadir actividad de un alumno.
 * Implementa la interfaz Initializable de JavaFX para inicializar la ventana.
 */

public class AnadirActividadDeAlumno implements Initializable {
    @javafx.fxml.FXML
    private DatePicker DatePickerFecha;
    @javafx.fxml.FXML
    private Spinner spinnerHoras;
    @javafx.fxml.FXML
    private RadioButton rbDual;
    @javafx.fxml.FXML
    private RadioButton rbFCT;
    @javafx.fxml.FXML
    private TextField txtActividad;
    @javafx.fxml.FXML
    private TextField txtObservaciones;
    @javafx.fxml.FXML
    private Label labelNombre;
    @javafx.fxml.FXML
    private Button btnAñadir;
    @javafx.fxml.FXML
    private Button btnCancelar;
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final ActividadDAO actividadDAO = new ActividadDAO();

    /**
     * Método de inicialización al cargar la interfaz.
     *
     * @param url            La ubicación relativa al archivo FXML.
     * @param resourceBundle El recurso de objetos específico del locale.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Inicialización de la ventana, asignación de eventos, etc.

        labelNombre.setText("Bienvenido/a " + Session.getCurrentAlumno().getNombre());
        spinnerHoras.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory.IntegerSpinnerValueFactory(0, 8, 1, 1));

        rbDual.setToggleGroup(toggleGroup);
        rbFCT.setToggleGroup(toggleGroup);

    }

    /**
     * Método llamado al hacer clic en el botón de añadir actividad.
     *
     * @param actionEvent El evento de acción del botón.
     * @throws IOException Si ocurre un error de E/S.
     */
    @javafx.fxml.FXML
    public void btnAnadir(ActionEvent actionEvent) throws IOException {
        // Lógica para añadir una actividad al alumno
        Alumno alumnoAsociado = Session.getCurrentAlumno();
        Actividad actividad = new Actividad();
        actividad.setAlumno(alumnoAsociado);

        if (txtActividad.getText().length() > 1) {
            actividad.setActividad(txtActividad.getText());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("El campo Actividad no puede estar vacío");
            alert.showAndWait();
        }
        if (txtObservaciones.getText().length() > 1) {
            actividad.setObservaciones(txtObservaciones.getText());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("El campo Observaciones no puede estar vacío");
            alert.showAndWait();
        }

        actividad.setHoras((Integer) spinnerHoras.getValue());

        //obtener la fecha seleccionada
        LocalDate fechaseleccionada = DatePickerFecha.getValue();

        if (fechaseleccionada != null) {
            LocalDate fechaSQL = (fechaseleccionada);
            actividad.setFecha(fechaSQL);
        } else {
            //alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("El campo Fecha no puede estar vacío");
            alert.showAndWait();
        }


        // obtener el tipo de practica.
        RadioButton seleccion = (RadioButton) toggleGroup.getSelectedToggle();
        if (seleccion != null) {
            if (seleccion == rbDual) {
                actividad.setTipo(Tips.DUAL);
            } else if (seleccion == rbFCT) {
                actividad.setTipo(Tips.FCT);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("El campo Tipo no puede estar vacío");
                alert.showAndWait();
            }
        }

        if (actividad.getID_Actividad() != null) {
            actividadDAO.update(actividad);
        } else {
            actividadDAO.save(actividad);
        }

        Session.getCurrentAlumno().getActividad_diaria().clear();
        Session.getCurrentAlumno().getActividad_diaria().addAll(actividadDAO.getActividadAlumno(Session.getCurrentAlumno()));

        try {
            App.changeScene("PaginaAlumno.fxml", "Pagina Alumno");
        } catch (IOException e) {
            throw new IOException(e);
        }


    }

    /**
     * Método llamado al hacer clic en el botón de cancelar.
     *
     * @param actionEvent El evento de acción del botón.
     */

    @javafx.fxml.FXML
    public void cancelar(ActionEvent actionEvent) {
        // Lógica para cancelar la operación y volver a la página del alumno
        try {
            App.changeScene("PaginaAlumno.fxml", "Pagina Alumno");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}