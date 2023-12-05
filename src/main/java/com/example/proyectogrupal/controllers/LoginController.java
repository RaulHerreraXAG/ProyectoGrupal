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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}