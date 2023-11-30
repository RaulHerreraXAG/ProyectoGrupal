package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.Main;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.profesor.Profesor;
import com.example.proyectogrupal.profesor.ProfesorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class logincontrollers implements Initializable
{
    @javafx.fxml.FXML
    private VBox vBoxFondo2;
    @javafx.fxml.FXML
    private TextField txtCorreo;
    @javafx.fxml.FXML
    private PasswordField txtPassword;
    @javafx.fxml.FXML
    private Button btnInciarSesion;


    public logincontrollers() {
    }
    @javafx.fxml.FXML
    public void SignIn(ActionEvent actionEvent) {
        String email = txtCorreo.getText();
        String password  = txtPassword.getText();

         Profesor profesor = (new ProfesorDAO()).validateUser(email, password);
        Session.setProfesor(profesor);
            try {
                Main.changeScene("interfazProfe.fxml", "Interfaz Profesorado");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}