package com.example.proyectogrupal.actividad;

import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.domain.DAO;
import com.example.proyectogrupal.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase implementa las operaciones DAO para la clase Actividad.
 */
public class ActividadDAO implements DAO<Actividad> {
    /**
     * Obtiene todas las actividades de la base de datos.
     *
     * @return Devuelve una lista con todas las actividades.
     */
    @Override
    public ArrayList<Actividad> getAll() {
        var salida = new ArrayList<Actividad>(0);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Actividad> query = session.createQuery("from Actividad ", Actividad.class);
            salida = (ArrayList<Actividad>) query.getResultList();
        }
        return salida;
    }

    /**
     * Obtiene una actividad por su ID.
     *
     * @param id Identificador del elemento a buscar.
     * @return La actividad correspondiente al ID proporcionado.
     */
    @Override
    public Actividad get(Long id) {
        var salida = new Actividad();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            salida = session.get(Actividad.class, id);
        }
        return salida;
    }

    /**
     * Guarda una nueva actividad en la base de datos.
     *
     * @param data Elemento del tipo T a guardar en la base de datos.
     * @return La actividad guardada.
     */
    @Override
    public Actividad save(Actividad data) {
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
     * Actualiza una actividad existente en la base de datos.
     *
     * @param data Elemento del tipo T a actualizar en la base de datos.
     */
    @Override
    public void update(Actividad data) {
        try (org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();

            Actividad g = s.get(Actividad.class, data.getID_Actividad());
            Actividad.merge(data, g);
            t.commit();

        }
    }


    /**
     * Elimina una actividad de la base de datos.
     *
     * @param data Elemento del tipo T a eliminar de la base de datos.
     */
    @Override
    public void delete(Actividad data) {
        if (data != null && data.getID_Actividad() != null) {
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                Actividad actividad = session.get(Actividad.class, data.getID_Actividad());

                if (actividad != null) {
                    session.remove(actividad);
                } else {
                    System.out.println("La actividad con ID " + data.getID_Actividad() + " no existe.");
                }
            });
        } else {
            System.out.println("La entidad o su ID son nulos.");
        }
    }

    /**
     * Calcula el total de horas de una lista de actividades.
     *
     * @param horas Lista de actividades.
     * @return Total de las horas de las actividades.
     */
    public int calcularTotalHoras(List<Actividad> horas) {
        int totalHoras = 0;
        for (Actividad actividad : horas) {
            totalHoras += actividad.getHoras();
        }
        return totalHoras;
    }

    /**
     * Obtiene todas las actividades de un alumno específico.
     *
     * @param currentAlumno El alumno del que se desean obtener las actividades.
     * @return Lista de actividades del alumno proporcionado.
     */
    public List<Actividad> getActividadAlumno(Alumno currentAlumno) {
        List<Actividad> actividadList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Actividad> query = session.createQuery("from Actividad where alumno = :alumno", Actividad.class);
            query.setParameter("alumno", currentAlumno);

            actividadList = query.getResultList();
        }
        return actividadList;
    }

}
