package com.example.proyectogrupal.alumno;

import com.example.proyectogrupal.domain.DAO;
import com.example.proyectogrupal.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class AlumnoDAO implements DAO<Alumno> {


    @Override
    public ArrayList<Alumno> getAll() {
        var salida = new ArrayList<Alumno>(0);
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            Query<Alumno> query = sesion.createQuery("from Alumno", Alumno.class);
            salida = (ArrayList<Alumno>) query.getResultList();
        }
        return salida;
    }

    @Override
    public Alumno get(Long id) {
        var salida = new Alumno();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            salida = session.get(Alumno.class, id);
        }
        return salida;
    }

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

    @Override
    public void update(Alumno data) {
        try( org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()){
            Transaction t = s.beginTransaction();

            Alumno g = s.get(Alumno.class, data.getID_Alumno());
            Alumno.merge(data, g);
            t.commit();

        }

    }

    @Override
    public void delete(Alumno data) {
        HibernateUtil.getSessionFactory().inTransaction((session -> {
            Alumno alumno = session.get(Alumno.class, data.getID_Alumno());
            session.remove(alumno);
        }));

    }

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
}
