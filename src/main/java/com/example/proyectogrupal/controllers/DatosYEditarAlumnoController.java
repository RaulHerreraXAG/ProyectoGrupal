package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.actividad.ActividadDAO;
import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.alumno.AlumnoDAO;
import com.example.proyectogrupal.empresa.Empresa;
import com.example.proyectogrupal.empresa.EmpresaDAO;
import com.example.proyectogrupal.profesor.Profesor;
import com.example.proyectogrupal.profesor.ProfesorDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    private ComboBox<String> comboEmpresa;
    @javafx.fxml.FXML
    private DatePicker txtFecha;
    @javafx.fxml.FXML
    private ComboBox<String> comboProfesor;
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
    @javafx.fxml.FXML
    private Label labelnombre;
    @javafx.fxml.FXML
    private TextField txttelefono;
    private final ProfesorDAO profesorDAO = new ProfesorDAO();
    private final EmpresaDAO empresaDAO = new EmpresaDAO();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        labelnombre.setText(Session.getCurrentAlumno().getNombre() + " " + Session.getCurrentAlumno().getApellidos());

        txtNombre.setText(Session.getCurrentAlumno().getNombre());
        txtApellidos.setText(Session.getCurrentAlumno().getApellidos());
        txtContraseña.setText(Session.getCurrentAlumno().getContrasenya());
        txtDNI.setText(Session.getCurrentAlumno().getDNI());

        ActividadDAO actividadDAO=new ActividadDAO();
        List<Actividad> actividades = Session.getCurrentAlumno().getActividad_diaria();
        int horasTotales = actividadDAO.calcularTotalHoras(actividades);
        txtHorasRealizadas.setText(String.valueOf(horasTotales));

        int horasRestantes= (700-horasTotales);
        txtHorasPorRealizar.setText(String.valueOf(horasRestantes));

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
        txttelefono.setText(String.valueOf(Session.getCurrentAlumno().getTelefono()));
        txtObservaciones.setText(Session.getCurrentAlumno().getObservaciones());


        //TODO Tabla de actividades

        cFecha.setCellValueFactory((fila) ->{
            LocalDate fecha = fila.getValue().getFecha();
            String fechaformato= "";

            if(fecha != null){
                DateTimeFormatter nuevafecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                fechaformato = fecha.format(nuevafecha);
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

        String nombreEmpresa = comboEmpresa.getValue() != null ? comboEmpresa.getValue().toString() : null;
        Empresa empresaAsociada = empresaDAO.buscarEmpresaPorNombre(nombreEmpresa);
        alumno.setEmpresa(empresaAsociada);

        // Actualiza el profesor tutor del alumno
        String nombreProfesor = comboProfesor.getValue() != null ? comboProfesor.getValue().toString() : null;
        Profesor profesorAlumno = profesorDAO.buscarProfesorPorNombre(nombreProfesor);
        alumno.setTutor(profesorAlumno);

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

        //Con esto mantenemos la pantalla para en caso de cancelar quedarnos donde estabamos
        Scene currentScene = btnActualizar.getScene();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de eliminación");
        alert.setHeaderText("¿Está seguro de que desea eliminar al alumno?");
        alert.setContentText(Session.getCurrentAlumno().getNombre() + " " + Session.getCurrentAlumno().getApellidos());

        // Obtener el resultado del cuadro de diálogo
        Optional<ButtonType> result = alert.showAndWait();

        // Verificar si el usuario hizo clic en OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Usuario confirmó la eliminación, procede con la eliminación
            AlumnoDAO alumnoDAO = new AlumnoDAO();
            alumnoDAO.delete(Session.getCurrentAlumno());

            // Actualiza la lista de alumnos del profesor en la sesión
            Session.getCurrentProfesor().getAlumnos().clear();
            Session.getCurrentProfesor().getAlumnos().addAll(alumnoDAO.getAlumnosPorProfesor(Session.getCurrentProfesor()));

            try {
                App.changeScene("PaginaProfesor.fxml", "Inicio Profesor");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            //Si cancelamos volvemos a la tabla del alumno
            Stage currentStage = (Stage) btnActualizar.getScene().getWindow();
            currentStage.setScene(currentScene);
        }
    }

    @javafx.fxml.FXML
    public void actualizar(ActionEvent actionEvent) {

        // Obtén el alumno actual
        Alumno a = Session.getCurrentAlumno();

        if (txtNombre.getText().length() > 1) {
            a.setNombre(txtNombre.getText());
        }
        if (txtApellidos.getText().length() > 1) {
            a.setApellidos(txtApellidos.getText());
        }
        if (txtContraseña.getText().length()>1){
            a.setContrasenya(txtContraseña.getText());
        }
        if (txtDNI.getText().length()>1){
            a.setDNI(txtDNI.getText());
        }
        if (txtFecha.getValue() != null){
            a.setNacimiento(txtFecha.getValue());
        }
        if (txtEmail.getText().length()>1){
            a.setEmail(txtEmail.getText());
        }
        if (txttelefono.getText().length()>1){
            a.setTelefono(Integer.valueOf(txttelefono.getText()));
        }

        if(comboEmpresa.getValue().length()>1){
            String nombreEmpresaSeleccionada = comboEmpresa.getValue();
            Empresa empresaSeleccionada = empresaDAO.buscarEmpresaPorNombre(nombreEmpresaSeleccionada);
            a.setEmpresa(empresaSeleccionada);
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

        try {
            App.changeScene("PaginaProfesor.fxml","Inicio Profesor");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
