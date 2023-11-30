package com.example.proyectogrupal.profesor;

import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.domain.DAO;
import com.example.proyectogrupal.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class ProfesorDAO implements DAO<Profesor> {


    @Override
    public ArrayList<Profesor> getAll() {
        return null;
    }

    @Override
    public Profesor get(Integer id) {
        return null;
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


        return result;
    }
}
