package com.example.proyectogrupal.alumno;

import com.example.proyectogrupal.domain.DAO;
import com.example.proyectogrupal.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class AlumnoDAO implements DAO<Alumno> {


    @Override
    public ArrayList<Alumno> getAll() {
        return null;
    }

    @Override
    public Alumno get(Integer id) {
        return null;
    }

    @Override
    public Alumno save(Alumno data) {
        return null;
    }

    @Override
    public void update(Alumno data) {

    }

    @Override
    public void delete(Alumno data) {

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
