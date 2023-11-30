package com.example.proyectogrupal.empresa;

import com.example.proyectogrupal.domain.DAO;
import com.example.proyectogrupal.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class EmpresaDAO implements DAO<Empresa> {
    @Override
    public ArrayList<Empresa> getAll() {
        var salida = new ArrayList<Empresa>(0);
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            Query<Empresa> query = sesion.createQuery("from Empresa ", Empresa.class);
            salida = (ArrayList<Empresa>) query.getResultList();
        }
        return salida;
    }

    @Override
    public Empresa get(Long id) {
        var salida = new Empresa();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            salida = session.get(Empresa.class, id);
        }
        return salida;
    }


    @Override
    public Empresa save(Empresa data) {
        return null;
    }

    @Override
    public void update(Empresa data) {

    }

    @Override
    public void delete(Empresa data) {

    }
}
