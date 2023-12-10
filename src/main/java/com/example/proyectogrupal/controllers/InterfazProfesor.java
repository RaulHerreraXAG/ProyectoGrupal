package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.alumno.Alumno;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InterfazProfesor implements Initializable {
    @javafx.fxml.FXML
    private TableColumn<Alumno, String> cNombre;
    @javafx.fxml.FXML
    private TableColumn<Alumno, String> cApellidos;
    @javafx.fxml.FXML
    private TableColumn<Alumno, String> cEmail;
    @javafx.fxml.FXML
    private TableColumn<Alumno, String> cTTelefono;
    @javafx.fxml.FXML
    private Button RegistraAlumnos;
    @javafx.fxml.FXML
    private Button RegistarEmpresa;
    @javafx.fxml.FXML
    private TableView<Alumno> TvAlumnos;

    ObservableList<Alumno> observableList = FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private Button btncerrarsesion;
    @javafx.fxml.FXML
    private Label labelProfesor;
    @FXML
    private Button TablaEmpresa;

    /**
     * Método que se ejecuta al presionar el botón para registrar un alumno.
     *
     * @param actionEvent Evento de acción que desencadena el método.
     * @throws IOException Excepción de entrada/salida.
     */
    @javafx.fxml.FXML
    public void RegistrarA(ActionEvent actionEvent) throws IOException {
        App.changeScene("registrar-alumno.fxml", "Registrar Alumno");
    }

    /**
     * Método de inicialización de la interfaz.
     *
     * @param url            URL de la ubicación del objeto.
     * @param resourceBundle ResourceBundle que contiene los recursos específicos del local.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        labelProfesor.setText("Bienvenido " + Session.getCurrentProfesor().getNombre());


        cNombre.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getNombre());
        });
        cApellidos.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getApellidos());
        });
        cEmail.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getEmail());
        });
        cTTelefono.setCellValueFactory((fila) -> {
            String telefono = String.valueOf(fila.getValue().getTelefono());
            return new SimpleStringProperty(telefono);
        });
        observableList.addAll(Session.getCurrentProfesor().getAlumnos());
        TvAlumnos.setItems(observableList);

        TvAlumnos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Alumno alumnoselect = TvAlumnos.getSelectionModel().getSelectedItem();
                if (alumnoselect != null) {
                    Session.setCurrentAlumno(alumnoselect);
                    try {
                        App.changeScene("DatosYEditarAlumno.fxml", "Editar Alumno");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    /**
     * Actualiza la tabla de alumnos en la interfaz gráfica.
     * Borra los elementos existentes en la tabla y luego agrega todos los alumnos del profesor actual.
     */
    public void actualizarTablaAlumnos() {
        TvAlumnos.getItems().clear();
        TvAlumnos.getItems().addAll(Session.getCurrentProfesor().getAlumnos());
    }


    /**
     * Método que maneja el evento de registrar empresas.
     *
     * @param actionEvent Evento de acción que desencadena el método.
     * @throws IOException Excepción de entrada/salida.
     */
    @javafx.fxml.FXML
    public void RegistrarEmp(ActionEvent actionEvent) throws IOException {
        App.changeScene("registrar-empresas.fxml", "Registrar Empresa");

    }

    /**
     * Método para cerrar la sesión del profesor.
     *
     * @param actionEvent Evento de acción que desencadena el método.
     */
    @javafx.fxml.FXML
    public void cerrarsesion(ActionEvent actionEvent) {
        try {
            App.changeScene("login.fxml", "Login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para acceder al menú de información de empresas.
     *
     * @param actionEvent Evento de acción que desencadena el método.
     * @throws IOException Excepción de entrada/salida.
     */

    @FXML
    public void MenuEmpresa(ActionEvent actionEvent) throws IOException {
        App.changeScene("InformacionEmpresa.fxml", "Tus Empresas");
    }
}