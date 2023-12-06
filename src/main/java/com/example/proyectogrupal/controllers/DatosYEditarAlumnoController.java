package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.alumno.AlumnoDAO;
import com.example.proyectogrupal.empresa.Empresa;
import com.example.proyectogrupal.empresa.EmpresaDAO;
import com.example.proyectogrupal.profesor.Profesor;
import com.example.proyectogrupal.profesor.ProfesorDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
    private TableColumn<Actividad, String> cFecha;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cPractica;
    @javafx.fxml.FXML
    private TableColumn<Actividad,String> cHoras;
    @javafx.fxml.FXML
    private TableColumn<Actividad,String> cActividad;
    @javafx.fxml.FXML
    private TableColumn<Actividad,String> cObservaciones;
    @javafx.fxml.FXML
    private TableView tablaeditAlum;
    @javafx.fxml.FXML
    private TextField txtContraseña;

    private final ProfesorDAO profesorDAO = new ProfesorDAO();
    private final EmpresaDAO empresaDAO = new EmpresaDAO();
    @javafx.fxml.FXML
    private Label labelnombre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        labelnombre.setText(Session.getCurrentAlumno().getNombre() + " " + Session.getCurrentAlumno().getApellidos());

        txtNombre.setText(Session.getCurrentAlumno().getNombre());
        txtApellidos.setText(Session.getCurrentAlumno().getApellidos());
        txtContraseña.setText(Session.getCurrentAlumno().getContrasenya());
        txtDNI.setText(Session.getCurrentAlumno().getDNI());

        //Esto es para poder pasar un DataPicker a otro sino no deja pasar los datos
        LocalDate fechana = Session.getCurrentAlumno().getNacimiento();
        txtFecha.setValue(fechana);

        //TODO Fumada de como añadir la empresa asociada al alumno al combo y te aparezca :)
        Alumno alumno = Session.getCurrentAlumno();
        Empresa empresaasociada = alumno.getEmpresa();
        //Obtener las empresas
        List<Empresa> allempresas = empresaDAO.getAll();
        //carga las empresas
        for (Empresa empresa : allempresas){
            comboEmpresa.getItems().add(empresa.getNombre());
        }
        //Con esto elegimos la empresa que esta con el alumno
        comboEmpresa.setValue(empresaasociada != null ? empresaasociada.getNombre(): null);

        //TODO Aqui añado el combo de Profesores

        Profesor profesoralumno = alumno.getTutor();
        List<Profesor> allprofesores = profesorDAO.getAll();
        for (Profesor profesor : allprofesores){
            comboProfesor.getItems().add(profesor.getNombre());
        }
        comboProfesor.setValue(profesoralumno != null ? profesoralumno.getNombre(): null);

        txtEmail.setText(Session.getCurrentAlumno().getEmail());
        txtObservaciones.setText(Session.getCurrentAlumno().getObservaciones());


        //TODO Tabla de actividades

        cFecha.setCellValueFactory((fila) ->{
            Date fecha = fila.getValue().getFecha();
            String fechaformato= "";

            if(fecha != null){
                SimpleDateFormat nuevafecha = new SimpleDateFormat("dd-MM-yyyy");
                fechaformato = nuevafecha.format(fecha);
            }
            return new SimpleStringProperty(fechaformato);

        });

        cPractica.setCellValueFactory((fila) ->{
            String practicas = String.valueOf(fila.getValue().getTipo());
            return new SimpleStringProperty(practicas);
        });
        cHoras.setCellValueFactory((fila)->{
            String horas = String.valueOf(fila.getValue().getHoras());
            return new SimpleStringProperty(horas);
        });
        cActividad.setCellValueFactory((fila)->{
            String actividad = String.valueOf(fila.getValue().getActividad());
            return new SimpleStringProperty(actividad);
        });
        cObservaciones.setCellValueFactory((fila)->{
            String obsevaciones = String.valueOf(fila.getValue().getObservaciones());
            return new SimpleStringProperty(obsevaciones);
        });


        Session.setCurrentAlumno((new AlumnoDAO().get(Session.getCurrentAlumno().getID_Alumno())));
        tablaeditAlum.getItems().addAll(Session.getCurrentAlumno().getActividad_diaria());

    }

    @javafx.fxml.FXML
    public void cancelar(ActionEvent actionEvent) {
        try {
            App.changeScene("PaginaProfesor.fxml","Pagina Profesor");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void eliminar(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void actualizar(ActionEvent actionEvent) {
    }
}
