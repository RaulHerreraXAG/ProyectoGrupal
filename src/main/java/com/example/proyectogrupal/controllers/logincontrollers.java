package com.example.proyectogrupal.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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



    @javafx.fxml.FXML
    public void SignIn(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}