package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.alumno.AlumnoDAO;
import com.example.proyectogrupal.empresa.Empresa;
import com.example.proyectogrupal.empresa.EmpresaDAO;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class EditarEmpresa
{
    @javafx.fxml.FXML
    private TextField txtTelefono;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextField txtEmail;
    @javafx.fxml.FXML
    private TextField txtResponsable;
    @javafx.fxml.FXML
    private TextField txtObservaciones;
    @javafx.fxml.FXML
    private Button btnActualizar;
    @javafx.fxml.FXML
    private Button btnEliminar;
    @javafx.fxml.FXML
    private Button btnCancelar;

    private final EmpresaDAO empresaDAO = new EmpresaDAO();

    @javafx.fxml.FXML
    public void initialize()  {
        txtNombre.setText(Session.getCurrentEmpresa().getNombre());
        txtEmail.setText(Session.getCurrentEmpresa().getEmail());
        txtTelefono.setText(String.valueOf(Session.getCurrentEmpresa().getTelefono()));
        txtResponsable.setText(Session.getCurrentEmpresa().getResponsable());
        txtObservaciones.setText(Session.getCurrentEmpresa().getObservaciones());
    }

    @javafx.fxml.FXML
    public void Actualizar(ActionEvent actionEvent) throws IOException {
        Empresa e = Session.getCurrentEmpresa();

        if (txtNombre.getText().length() > 1) {
            e.setNombre(txtNombre.getText());
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Escriba el nombre correspondiente de la empresa.");
            alert.showAndWait();
        }
        if (txtEmail.getText().length() > 1) {
            e.setEmail(txtEmail.getText());
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Escriba el correo correspondiente de la empresa");
            alert.showAndWait();
        }
        if (txtResponsable.getText().length() > 1) {
            e.setResponsable(txtResponsable.getText());
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Escriba el responsable correspondiente de la empresa");
            alert.showAndWait();
        }
        if (txtTelefono.getText().length()>1){
            e.setTelefono(Integer.valueOf(txtTelefono.getText()));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Escriba el telefono correspondiente de la empresa");
            alert.showAndWait();
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

        App.changeScene("InformacionEmpresa.fxml","Empresas");
    }


    @javafx.fxml.FXML
    public void Eliminar(ActionEvent actionEvent) {
        //Con esto mantenemos la pantalla para en caso de cancelar quedarnos donde estabamos
        Scene currentScene = btnActualizar.getScene();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de eliminación");
        alert.setHeaderText("¿Está seguro de que desea eliminar la empresa?");
        alert.setContentText(Session.getCurrentEmpresa().getNombre());

        // Obtener el resultado del cuadro de diálogo
        Optional<ButtonType> result = alert.showAndWait();

        // Verificar si el usuario hizo clic en OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Usuario confirmó la eliminación, procede con la eliminación de la empresa y actualización de actividades
            EmpresaDAO empresaDAO = new EmpresaDAO();
            empresaDAO.delete(Session.getCurrentEmpresa());

            // Luego, actualiza las actividades asociadas a la empresa eliminada
            empresaDAO.actualizarActividadesPorEmpresa(Session.getCurrentEmpresa());

            try {
                App.changeScene("InformacionEmpresa.fxml", "Información de las empresas");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Si cancelamos volvemos a la tabla del alumno
            Stage currentStage = (Stage) btnActualizar.getScene().getWindow();
            currentStage.setScene(currentScene);
        }
    }


    @javafx.fxml.FXML
    public void Cancelar(ActionEvent actionEvent) {
        try {
            App.changeScene("InformacionEmpresa.fxml","Información de las empresas");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}