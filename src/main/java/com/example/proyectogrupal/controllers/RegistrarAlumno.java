package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.alumno.AlumnoDAO;
import com.example.proyectogrupal.empresa.Empresa;
import com.example.proyectogrupal.empresa.EmpresaDAO;
import com.example.proyectogrupal.profesor.Profesor;
import com.example.proyectogrupal.profesor.ProfesorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarAlumno implements Initializable
{
    @javafx.fxml.FXML
    private TextField txtnombre;
    @javafx.fxml.FXML
    private TextField txtapellido;
    @javafx.fxml.FXML
    private PasswordField txtcontraseña;
    @javafx.fxml.FXML
    private TextField txtDNI;
    @javafx.fxml.FXML
    private DatePicker txtfechana;
    @javafx.fxml.FXML
    private TextField txtemail;
    @javafx.fxml.FXML
    private TextField txttelefono;
    @javafx.fxml.FXML
    private ComboBox<String> comboEmpresa;
    @javafx.fxml.FXML
    private Button btncancelar;
    @javafx.fxml.FXML
    private Button btnregistrar;
    @javafx.fxml.FXML
    private Spinner<Integer> spinnerDual;
    @javafx.fxml.FXML
    private Spinner<Integer> spinnerFCT;
    @javafx.fxml.FXML
    private TextArea textObserva;

    private final AlumnoDAO alumnoDAO = new AlumnoDAO();
    private final ProfesorDAO profesorDAO = new ProfesorDAO();
    private final EmpresaDAO empresaDAO = new EmpresaDAO();
    @javafx.fxml.FXML
    private TextField txtProfesor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        spinnerDual.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,700,1,1));
        spinnerFCT.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,700,1,1));

        txtProfesor.setText(Session.getCurrentProfesor().getNombre());

        //Añadido nombre empresas
        var nombreempresas = empresaDAO.getnombreEmpresas();
        comboEmpresa.getItems().addAll(nombreempresas);

    }



    @javafx.fxml.FXML
    public void cancelar(ActionEvent actionEvent) throws IOException {
        App.changeScene("PaginaProfesor.fxml","Pagina Profesor");
    }


    //TODO Cambiar empresa y tutor de tipo varchar porque estan en INT

    @javafx.fxml.FXML
    public void registrarAlumno(ActionEvent actionEvent) throws IOException {

        Alumno a = new Alumno();

        if (txtnombre.getText().length() > 1) {
            a.setNombre(txtnombre.getText());
        }
        if (txtapellido.getText().length() > 1) {
            a.setApellidos(txtapellido.getText());
        }
        if (txtcontraseña.getText().length()>1){
            a.setContrasenya(txtcontraseña.getText());
        }
        if (txtDNI.getText().length()>1){
            a.setDNI(txtDNI.getText());
        }
        if (txtfechana.getValue() != null){
            a.setNacimiento(txtfechana.getValue());
        }
        if (txtemail.getText().length()>1){
            a.setEmail(txtemail.getText());
        }
        if (txttelefono.getText().length()>1){
            a.setTelefono(Integer.valueOf(txttelefono.getText()));
        }

        if(comboEmpresa.getValue().length()>1){
            String nombreEmpresaSeleccionada = comboEmpresa.getValue();
            Empresa empresaSeleccionada = empresaDAO.buscarEmpresaPorNombre(nombreEmpresaSeleccionada);
            a.setEmpresa(empresaSeleccionada);
        }

        if (txtProfesor.getText().length()>1){
            a.setTutor(Session.getCurrentProfesor());
        }

        a.setHorasDual(spinnerDual.getValue());
        a.setHorasFCT(spinnerFCT.getValue());
        if (textObserva.getText() != null){
            a.setObservaciones(textObserva.getText());
        }

        AlumnoDAO alumnoDAO = new AlumnoDAO();

        if (a.getID_Alumno() != null) {
            // Si el alumno ya tiene un ID, entonces ya existe en la base de datos y actualizamos
            alumnoDAO.update(a);
        } else {
            // Si el alumno no tiene un ID, es nuevo y lo guardamos
            alumnoDAO.save(a);
        }

        Session.getCurrentProfesor().getAlumnos().clear();
        Session.getCurrentProfesor().getAlumnos().addAll(alumnoDAO.getAlumnosPorProfesor(Session.getCurrentProfesor()));

        App.changeScene("PaginaProfesor.fxml","Inicio Profesor");
    }

}