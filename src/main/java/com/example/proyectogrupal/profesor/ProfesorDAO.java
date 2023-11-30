package com.example.proyectogrupal.profesor;

import com.example.proyectogrupal.domain.DAO;
import com.example.proyectogrupal.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class ProfesorDAO implements DAO<Profesor> {
    @Override
    public ArrayList<Profesor> getAll() {
        var salida = new ArrayList<Profesor>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Profesor> q = s.createQuery("from Profesor ", Profesor.class);
            salida = (ArrayList<Profesor>) q.getResultList();
        }
        return salida;
    }

    @Override
    public Profesor get(Integer id) {
        var salida = new Profesor();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(Profesor.class, id);
        }

        return salida;
    }

    @Override
    public Profesor save(Profesor data) {
        return null;
    }

    @Override
    public void update(Profesor data) {

    }

    @Override
    public void delete(Profesor data) {

    }

    public Profesor validateUser(String email, String password) {
        Profesor result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Profesor> q = session.createQuery("from Profesor where email=:u and contrasenya=:p", Profesor.class);
            q.setParameter("u", email);
            q.setParameter("p", password);

            try {
                result = q.getSingleResult();
            } catch (Exception e) {
                e.printStackTrace(); // Mostrar la traza completa de la excepción
            }
        }
        return result;
    }

}
