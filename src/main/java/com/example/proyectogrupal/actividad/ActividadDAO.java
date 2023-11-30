package com.example.proyectogrupal.actividad;

import com.example.proyectogrupal.domain.DAO;
import com.example.proyectogrupal.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

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
        return null;
    }

    @Override
    public void update(Actividad data) {

    }

    @Override
    public void delete(Actividad data) {

    }
}
