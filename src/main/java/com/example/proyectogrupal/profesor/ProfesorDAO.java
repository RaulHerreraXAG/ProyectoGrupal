package com.example.proyectogrupal.profesor;

import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.domain.DAO;
import com.example.proyectogrupal.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class ProfesorDAO implements DAO<Profesor> {


    @Override
        public ArrayList<Profesor> getAll() {
            var salida = new ArrayList<Profesor>(0);
            try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
                Query<Profesor> query = sesion.createQuery("from Profesor", Profesor.class);
                salida = (ArrayList<Profesor>) query.getResultList();
            }
            return salida;
    }

    @Override
    public Profesor get(Long id) {
        var salida = new Profesor();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            salida = session.get(Profesor.class, id);
        }
        return salida;
    }

    @Override
    public Profesor save(Profesor data) {
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
    public void update(Profesor data) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Actualizar el pedido en la base de datos
            session.update(data);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }


    }

    @Override
    public void delete(Profesor data) {
        HibernateUtil.getSessionFactory().inTransaction((session -> {
            Alumno alumno = session.get(Alumno.class, data.getID_Profesor());
            session.remove(alumno);
        }));
    }
    public Profesor validateUser(String email, String contrasenya) {
        Profesor result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Profesor> query = session.createQuery("from Profesor where email=:e and contrasenya=:c", Profesor.class);
            query.setParameter("e", email);
            query.setParameter("c", contrasenya);

            try {
                result = query.getSingleResult();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("aquiiiiiiiii");
        System.out.println(result);
        return result;
    }
}
