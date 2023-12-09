package com.example.proyectogrupal.actividad;

import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.domain.DAO;
import com.example.proyectogrupal.domain.HibernateUtil;
import com.example.proyectogrupal.profesor.Profesor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActividadDAO  implements DAO<Actividad> {
    @Override
    public ArrayList<Actividad> getAll() {
        var salida = new ArrayList<Actividad>(0);
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Actividad> query = session.createQuery("from Actividad ", Actividad.class);
            salida = (ArrayList<Actividad>) query.getResultList();
        }
        return salida;
    }

    @Override
    public Actividad get(Long id) {
        var salida = new Actividad();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            salida = session.get(Actividad.class, id);
        }
        return salida;
    }

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



    @Override
    public void update(Actividad data) {
        try (org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();

            Actividad g = s.get(Actividad.class, data.getID_Actividad());
            Actividad.merge(data, g);
            t.commit();

        }
    }


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
    public int calcularTotalHoras(List<Actividad> horas){
        int totalHoras = 0;
        for (Actividad actividad : horas) {
            totalHoras += actividad.getHoras();
        }
        return totalHoras;
    }

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
