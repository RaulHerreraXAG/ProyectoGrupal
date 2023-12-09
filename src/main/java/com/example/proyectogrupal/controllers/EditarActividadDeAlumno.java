package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.actividad.ActividadDAO;
import com.example.proyectogrupal.actividad.Tips;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditarActividadDeAlumno implements Initializable {

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
    private Button btnActualizar;

    private final ToggleGroup toggleGroup = new ToggleGroup();
    @javafx.fxml.FXML
    private Button btnEliminar;
    @javafx.fxml.FXML
    private Button btnCancelar1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        labelNombre.setText("Bienvenido/a " + Session.getCurrentAlumno().getNombre());
        txtActividad.setText(Session.getCurrentActividad().getActividad());
        txtObservaciones.setText(Session.getCurrentActividad().getObservaciones());

        spinnerHoras.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 8, Math.toIntExact(Session.getCurrentActividad().getHoras()), 1));

        //Esto es para poder pasar un DataPicker a otro sino no deja pasar los datos
        LocalDate actfecha = Session.getCurrentActividad().getFecha();
        DatePickerFecha.setValue(actfecha);

        rbDual.setToggleGroup(toggleGroup);
        rbFCT.setToggleGroup(toggleGroup);

        var tipo = Session.getCurrentActividad().getTipo();

        if (tipo != null) {
            switch (tipo) {
                case DUAL:
                    toggleGroup.selectToggle(rbDual);
                    break;
                case FCT:
                    toggleGroup.selectToggle(rbFCT);
                    break;
                default:
                    break;
            }
        }
    }


    @javafx.fxml.FXML
    public void cancelar(ActionEvent actionEvent) {
        try {
            App.changeScene("PaginaAlumno.fxml", "Pagina Alumno");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @javafx.fxml.FXML
    public void btnActualizar(ActionEvent actionEvent) throws IOException {
        // Obtener la instancia de la actividad actual
        Actividad actividad = Session.getCurrentActividad();

        // Actualizar las propiedades con los valores de los controles de la interfaz de usuario
        if (txtActividad.getText().length() > 1) {
            actividad.setActividad(txtActividad.getText());
        }
        if (txtObservaciones.getText().length() > 1) {
            actividad.setObservaciones(txtObservaciones.getText());
        }

        actividad.setHoras((Integer) spinnerHoras.getValue());

        // Obtener la fecha seleccionada
        LocalDate fechaSeleccionada = DatePickerFecha.getValue();
        if (fechaSeleccionada != null) {
            actividad.setFecha(fechaSeleccionada);
        } else {
            System.out.println("Error: Debes seleccionar una fecha.");
            return;
        }

        // Obtener el tipo de práctica
        RadioButton seleccion = (RadioButton) toggleGroup.getSelectedToggle();
        if (seleccion != null) {
            if (seleccion == rbDual) {
                actividad.setTipo(Tips.DUAL);
            } else if (seleccion == rbFCT) {
                actividad.setTipo(Tips.FCT);
            }
        }

        ActividadDAO actividadDAO = new ActividadDAO();

        if (actividad.getID_Actividad() != null){
            actividadDAO.update(actividad);
        } else {
            actividadDAO.save(actividad);
        }

        try {
            App.changeScene("PaginaAlumno.fxml", "Pagina Alumno");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void eliminar(ActionEvent actionEvent) throws IOException {

        ActividadDAO actividadDAO = new ActividadDAO();
        Actividad actividadseleccionada = Session.getCurrentActividad();

        // Crear un cuadro de diálogo de confirmación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Actividad");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que deseas eliminar esta actividad?");

        // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
        Optional<ButtonType> result = alert.showAndWait();

        // Verificar la respuesta del usuario
        if (result.isPresent() && result.get() == ButtonType.OK) {
            actividadDAO.delete(actividadseleccionada);

            Session.getCurrentAlumno().getActividad_diaria().clear();
            Session.getCurrentAlumno().getActividad_diaria().addAll(actividadDAO.getActividadAlumno(Session.getCurrentAlumno()));
        }
        try{

            App.changeScene("PaginaAlumno.fxml", "Pagina Alumno");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

