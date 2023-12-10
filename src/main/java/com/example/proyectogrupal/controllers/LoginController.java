package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.alumno.AlumnoDAO;
import com.example.proyectogrupal.profesor.Profesor;
import com.example.proyectogrupal.profesor.ProfesorDAO;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controlador para la ventana de inicio de sesión.
 * Implementa la interfaz Initializable de JavaFX para la inicialización de componentes.
 */

public class LoginController implements Initializable
{
    @javafx.fxml.FXML
    private VBox vBoxFondo2;
    @javafx.fxml.FXML
    private TextField txtCorreo;
    @javafx.fxml.FXML
    private PasswordField txtPassword;
    @javafx.fxml.FXML
    private Button btnInciarSesion;


    /**
     * Método que maneja el evento de inicio de sesión.
     * Valida el usuario (alumno o profesor) con el correo electrónico y contraseña proporcionados.
     *
     * @param actionEvent Evento de acción que desencadena el método.
     */
    @javafx.fxml.FXML
    public void SignIn(ActionEvent actionEvent) {
        String email = txtCorreo.getText();
        String contrasenya = txtPassword.getText();

        //comprobamos si el usuario esta en la base de datos
        Alumno a = (new AlumnoDAO()).validateUser(email, contrasenya);
        Profesor p = (new ProfesorDAO()).validateUser(email, contrasenya);

        if (a != null) {
            //Guardamos el usuario en sesión e iremos a su ventana principal
            try {
                Session.setCurrentAlumno(a);
                App.changeScene("PaginaAlumno.fxml", "Página Alumno");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (p!=null){
            try {
                Session.setCurrentProfesor(p);
                App.changeScene("PaginaProfesor.fxml", "Página Profesor");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (a==null && p==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Email o contraseña incorrecto.");
            alert.showAndWait();
            txtCorreo.setText("");
            txtPassword.setText("");

        }

    }
    /**
     * Método de inicialización del controlador.
     *
     * @param url             URL de la ubicación del objeto.
     * @param resourceBundle  ResourceBundle que contiene los recursos específicos del local.
     */

    /**
     * Inicializa el controlador después de que su elemento raíz haya sido completamente procesado.
     *
     * Este método configura un listener para el evento de presionar tecla en el campo de contraseña (`txtPassword`).
     * Cuando se presiona la tecla "Enter", simula el clic en el botón de inicio de sesión (`btnInciarSesion`).
     * Esto proporciona una forma conveniente de iniciar sesión al presionar "Enter" en el campo de contraseña.
     *
     * @param url            La ubicación relativa del recurso raíz, o {@code null} si no se ha utilizado una ubicación relativa.
     * @param resourceBundle El paquete de recursos, o {@code null} si no hay paquete de recursos.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Simular el clic en el botón de inicio de sesión
                btnInciarSesion.fire();
            }
        });
    }


    /**
     * Maneja el evento de "Olvidé mi contraseña", permitiendo a los usuarios restablecer sus contraseñas.
     *
     * Este método muestra un cuadro de diálogo para que el usuario ingrese su dirección de correo electrónico.
     * Luego, intenta buscar un Alumno o Profesor asociado a esa dirección de correo electrónico y, si se encuentra,
     * establece la sesión correspondiente y cambia a la escena de cambio de contraseña.
     *
     * @param event El evento que desencadenó la invocación de este método.
     *
     */
    public void clickolvidar(Event event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Olvidé mi contraseña");
        dialog.setHeaderText("Ingrese su correo electrónico en el cuadro de texto:");
        dialog.setContentText("Correo: ");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(email -> {
            AlumnoDAO alumnoDAO = new AlumnoDAO();
            ProfesorDAO profesorDAO = new ProfesorDAO();

            // Intentar obtener un alumno por correo electrónico
            Alumno alumno = alumnoDAO.getAlumnoByEmail(email);
            if (alumno != null) {
                Session.setCurrentAlumno(alumno);
                try {
                    App.changeScene("cambio-contraseña.fxml", "Cambiar Contraseña Alumno");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return;
            }

            // Intentar obtener un profesor por correo electrónico
            Profesor profesor = profesorDAO.getProfesorByEmail(email);
            if (profesor != null) {
                Session.setCurrentProfesor(profesor);
                try {
                    App.changeScene("cambio-contraseña.fxml", "Cambiar Contraseña Profesor");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return;
            }

            // Si no se encuentra ni alumno ni profesor, mostrar un mensaje de error
            mostrarAlerta("Error", "Correo electrónico no válido", Alert.AlertType.ERROR);
        });
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