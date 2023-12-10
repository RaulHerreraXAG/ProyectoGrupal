package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.alumno.AlumnoDAO;
import com.example.proyectogrupal.profesor.Profesor;
import com.example.proyectogrupal.profesor.ProfesorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la funcionalidad de cambio de contraseña.
 *
 * Esta clase gestiona la interfaz de usuario y las operaciones relacionadas con el cambio de contraseña
 * para tanto alumnos como profesores.
 *
 * @author Tu Nombre
 * @version 1.0
 */
public class CambioContraseña implements Initializable {

    @javafx.fxml.FXML
    private VBox vBoxFondo2;

    @javafx.fxml.FXML
    private PasswordField txtPassword1;

    @javafx.fxml.FXML
    private PasswordField txtPassword2;

    @javafx.fxml.FXML
    private Button btncambiarcontraseña;

    @javafx.fxml.FXML
    private Label labeluser;

    /**
     * Inicializa la interfaz de usuario con el nombre del usuario actual.
     *
     * @param url No utilizado.
     * @param resourceBundle No utilizado.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Alumno currentAlumno = Session.getCurrentAlumno();
        Profesor currentProfesor = Session.getCurrentProfesor();

        if (currentAlumno != null) {
            labeluser.setText(currentAlumno.getNombre());
        } else if (currentProfesor != null) {
            labeluser.setText(currentProfesor.getNombre());
        }
    }

    /**
     * Maneja el evento de cambio de contraseña.
     *
     * @param actionEvent Evento de acción generado por el usuario.
     */
    @javafx.fxml.FXML
    public void cambiarcontraseña(ActionEvent actionEvent) {

        String newPassword1 = txtPassword1.getText();
        String newPassword2 = txtPassword2.getText();

        if (newPassword1.isEmpty() || newPassword2.isEmpty()) {
            mostrarAlerta("Error", "Por favor, complete ambos campos de contraseña.", Alert.AlertType.ERROR);
            return;
        }

        if (!newPassword1.equals(newPassword2)) {
            mostrarAlerta("Error", "Las contraseñas no coinciden.", Alert.AlertType.ERROR);
            return;
        }

        Alumno currentAlumno = Session.getCurrentAlumno();
        Profesor currentProfesor = Session.getCurrentProfesor();

        if (currentAlumno != null) {
            AlumnoDAO alumnoDAO = new AlumnoDAO();
            currentAlumno.setContrasenya(newPassword1);
            alumnoDAO.update(currentAlumno);
        } else if (currentProfesor != null) {
            ProfesorDAO profesorDAO = new ProfesorDAO();
            currentProfesor.setContrasenya(newPassword1);
            profesorDAO.update(currentProfesor);
        }

        mostrarAlerta("Éxito", "Contraseña actualizada exitosamente.", Alert.AlertType.INFORMATION);

        try {
            App.changeScene("login.fxml", "Login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Muestra una alerta con el título, mensaje y tipo de alerta dados.
     *
     * @param titulo El título de la alerta.
     * @param mensaje El mensaje a mostrar en la alerta.
     * @param tipo El tipo de alerta (por ejemplo, AlertType.ERROR, AlertType.INFORMATION).
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}