package com.example.proyectogrupal.alumno;

import com.example.proyectogrupal.domain.DAO;
import com.example.proyectogrupal.domain.HibernateUtil;
import org.hibernate.Session;
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
