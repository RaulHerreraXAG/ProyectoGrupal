package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.empresa.Empresa;
import com.example.proyectogrupal.empresa.EmpresaDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para mostrar información sobre las empresas.
 */

public class InformacionEmpresa implements Initializable {

    // Declaraciones de campos de la interfaz gráfica y observables

    @javafx.fxml.FXML
    private TableColumn<Empresa, String> cNombre;
    @javafx.fxml.FXML
    private TableColumn<Empresa, String> cEmail;
    @javafx.fxml.FXML
    private TableColumn<Empresa, String> cTelefono;
    @javafx.fxml.FXML
    private TableColumn<Empresa, String> cResponsable;
    @javafx.fxml.FXML
    private TableColumn<Empresa, String> cObservaciones;
    @javafx.fxml.FXML
    private Button btnVolver;
    @javafx.fxml.FXML
    private TableView<Empresa> tvEmpresa;

    private ObservableList<Empresa> observableList = FXCollections.observableArrayList();

    /**
     * Método de inicialización que configura las columnas y carga la información de las empresas en la tabla.
     *
     * @param url            La URL de la ubicación del archivo FXML.
     * @param resourceBundle Los recursos utilizados para localizar el archivo FXML.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Lógica para inicializar la tabla con la información de las empresas

        cNombre.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getNombre());
        });
        cResponsable.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getResponsable());
        });
        cEmail.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getEmail());
        });
        cTelefono.setCellValueFactory((fila) -> {
            String telefono = String.valueOf(fila.getValue().getTelefono());
            return new SimpleStringProperty(telefono);
        });
        cObservaciones.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getObservaciones());
        });

        observableList.addAll(new EmpresaDAO().getAll());
        tvEmpresa.setItems(observableList);

        tvEmpresa.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Empresa empresaselect = tvEmpresa.getSelectionModel().getSelectedItem();
                if (empresaselect != null) {
                    Session.setCurrentEmpresa(empresaselect);
                    try {
                        App.changeScene("EditarEmpresa.fxml", "Editar Empresa");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    /**
     * Método llamado al hacer clic en el botón de volver.
     *
     * @param actionEvent El evento de acción del botón.
     * @throws IOException Si ocurre un error de E/S al cambiar la escena.
     */

    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) throws IOException {
        // Lógica para volver a la página del profesor
        App.changeScene("PaginaProfesor.fxml", "Inicio Profesor");
    }


}