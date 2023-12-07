package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.empresa.Empresa;
import com.example.proyectogrupal.empresa.EmpresaDAO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistroEmpresa {
    @javafx.fxml.FXML
    private TextField txtEmail;
    @javafx.fxml.FXML
    private TextField txtTelefono;
    @javafx.fxml.FXML
    private TextField txtResponsable;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextField txtObservaciones;
    @javafx.fxml.FXML
    private Button btncancelar;
    @javafx.fxml.FXML
    private Button btnRegistrar;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void Cancelar(ActionEvent actionEvent) throws IOException {
        // Volvemos a la pagina principal del profesor
        App.changeScene("PaginaProfesor.fxml","Pagina Profesor");
    }

    @javafx.fxml.FXML
    public void Registrar(ActionEvent actionEvent) throws IOException {
        Empresa e = new Empresa();

        //Cogemos el texto de cada textfield con sus id y la guardamos en su responsable sitio

        if (txtNombre.getText().length() > 1) {
            e.setNombre(txtNombre.getText());
        }
        if (txtEmail.getText().length() > 1) {
            e.setEmail(txtEmail.getText());
        }
        if (txtResponsable.getText().length() > 1) {
            e.setResponsable(txtResponsable.getText());
        }
        if (txtTelefono.getText().length()>1){
            e.setTelefono(Integer.valueOf(txtTelefono.getText()));
        }
        if (txtObservaciones.getText() != null){
            e.setObservaciones(txtObservaciones.getText());
        }

        EmpresaDAO empresaDAO = new EmpresaDAO();

        if(e.getID_Empresa() != null){
            // Si la empresa ya contiene una ID, existe en la base de datos y se actualiza
            empresaDAO.update(e);
        }else{
            // Si la empresa no tiene id , se crea al ser nuevo
            empresaDAO.save(e);
        }

        if(txtNombre == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Escriba el nombre correspondiente de la empresa.");
            alert.showAndWait();
        }else{
            App.changeScene("PaginaProfesor.fxml","Pagina Profesor");
        }

        if(txtResponsable == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Escriba el responsable correspondiente de la empresa");
            alert.showAndWait();
        }else{
            App.changeScene("PaginaProfesor.fxml","Pagina Profesor");
        }

        if(txtEmail == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Escriba el correo correspondiente de la empresa");
            alert.showAndWait();
        }else{
            App.changeScene("PaginaProfesor.fxml","Pagina Profesor");
        }

        if(txtTelefono == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Escriba el telefono correspondiente de la empresa");
            alert.showAndWait();
        }else{
            App.changeScene("PaginaProfesor.fxml","Pagina Profesor");
        }




    }
}