package com.example.proyectogrupal.alumno;

import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.domain.DAO;
import com.example.proyectogrupal.domain.HibernateUtil;
import com.example.proyectogrupal.profesor.Profesor;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la lógica de acceso a datos para la entidad Alumno.
 */
public class AlumnoDAO implements DAO<Alumno> {
    /**
     * @return Devuelve una lista con todos los alumnos.
     */
    @Override
    public ArrayList<Alumno> getAll() {
        var salida = new ArrayList<Alumno>(0);
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            Query<Alumno> query = sesion.createQuery("from Alumno", Alumno.class);
            salida = (ArrayList<Alumno>) query.getResultList();
        }
        return salida;
    }

    /**
     * @param id Identificador del elemento a buscar.
     * @return Devuelve el alumno según el ID proporcionado.
     */
    @Override
    public Alumno get(Long id) {
        var salida = new Alumno();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            salida = session.get(Alumno.class, id);
        }
        return salida;
    }

    /**
     * Guarda un nuevo alumno en la base de datos.
     *
     * @param data Elemento del tipo T a guardar en la base de datos.
     * @return Devuelve el alumno guardado.
     */
    @Override
    public Alumno save(Alumno data) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                // Comienza la transacción.
                transaction = session.beginTransaction();

                // Guarda el nuevo ítem en la Base de Datos.
                session.save(data);

                // Commit de la transacción.
                transaction.commit();
            } catch (Exception e) {
                // Maneja cualquier excepción que pueda ocurrir durante la transacción.
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return data;
        }
    }

    /**
     * Actualiza un alumno existente en la base de datos.
     *
     * @param data Elemento del tipo T a actualizar en la base de datos.
     */
    @Override
    public void update(Alumno data) {
        try (org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();

            Alumno g = s.get(Alumno.class, data.getID_Alumno());
            Alumno.merge(data, g);
            t.commit();

        }

    }

    /**
     * Elimina un alumno de la base datos.
     *
     * @param data Elemento del tipo T a eliminar de la base de datos.
     */
    @Override
    public void delete(Alumno data) {
        HibernateUtil.getSessionFactory().inTransaction((session -> {
            Alumno alumno = session.get(Alumno.class, data.getID_Alumno());
            // Obtener y eliminar las actividades asociadas al alumno
            List<Actividad> actividades = alumno.getActividad_diaria();
            for (Actividad actividad : actividades) {
                session.remove(actividad);
            }
            // Eliminar al alumno
            session.remove(alumno);
        }));


    }

    /**
     * Válida el inicio de sesión del alumno.
     *
     * @param email       Email del alumno.
     * @param contrasenya Contraseña del alumno.
     * @return Devuelve el alumno si las credenciales son válidas.
     */
    public Alumno validateUser(String email, String contrasenya) {
        Alumno result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Alumno> query = session.createQuery("from Alumno where email=:e and contrasenya=:c", Alumno.class);
            query.setParameter("e", email);
            query.setParameter("c", contrasenya);

            try {
                result = query.getSingleResult();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


        return result;
    }

    /**
     * Obtiene una lista de alumnos asociados a un profesor.
     *
     * @param profesor El profesor del cual se desean obtener los alumnos.
     * @return Devuelve una lista de alumnos asociados al profesor.
     */
    public List<Alumno> getAlumnosPorProfesor(Profesor profesor) {
        List<Alumno> alumnos = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Alumno> query = session.createQuery("from Alumno where tutor = :profesor", Alumno.class);
            query.setParameter("profesor", profesor);

            alumnos = query.getResultList();
        }
        return alumnos;
    }

    /**
     * Obtiene un alumno basado en su dirección de correo electrónico.
     * <p>
     * Este método busca y devuelve un objeto Alumno asociado a la dirección de correo electrónico proporcionada.
     *
     * @param email La dirección de correo electrónico del alumno a buscar.
     * @return Un objeto Alumno si se encuentra, o null si no se encuentra ningún alumno con el correo electrónico dado.
     * HibernateException Si hay algún problema con la sesión de Hibernate.
     * @see Alumno
     */
    public Alumno getAlumnoByEmail(String email) {
        Alumno result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Alumno> query = session.createQuery("from Alumno where email=:e", Alumno.class);
            query.setParameter("e", email);

            try {
                result = query.getSingleResult();
            } catch (NoResultException e) {
                // No se encontró ningún usuario con ese correo electrónico
                System.out.println("Usuario no encontrado para el correo electrónico: " + email);
            } catch (Exception e) {
                // Otra excepción
                System.out.println("Error al buscar usuario por correo electrónico: " + e.getMessage());
            }
        }
        return result;
    }
}
