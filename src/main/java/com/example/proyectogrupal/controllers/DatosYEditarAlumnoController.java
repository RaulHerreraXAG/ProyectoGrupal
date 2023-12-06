package com.example.proyectogrupal.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DatosYEditarAlumnoController implements Initializable {
    @javafx.fxml.FXML
    private Button btnCancelar;
    @javafx.fxml.FXML
    private Button btnBorrar;
    @javafx.fxml.FXML
    private Button btnActualizar;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextField txtApellidos;
    @javafx.fxml.FXML
    private PasswordField txtContraseña;
    @javafx.fxml.FXML
    private TextField txtDNI;
    @javafx.fxml.FXML
    private ComboBox comboEmpresa;
    @javafx.fxml.FXML
    private DatePicker txtFecha;
    @javafx.fxml.FXML
    private ComboBox comboProfesor;
    @javafx.fxml.FXML
    private TextField txtEmail;
    @javafx.fxml.FXML
    private TextArea txtObservaciones;
    @javafx.fxml.FXML
    private TextField txtHorasRealizadas;
    @javafx.fxml.FXML
    private TextField txtHorasPorRealizar;
    @javafx.fxml.FXML
    private TableView tv;
    @javafx.fxml.FXML
    private TableColumn cFecha;
    @javafx.fxml.FXML
    private TableColumn cPractica;
    @javafx.fxml.FXML
    private TableColumn cHoras;
    @javafx.fxml.FXML
    private TableColumn cActividad;
    @javafx.fxml.FXML
    private TableColumn cObservaciones;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
