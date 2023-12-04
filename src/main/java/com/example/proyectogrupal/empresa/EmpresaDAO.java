package com.example.proyectogrupal.empresa;

import com.example.proyectogrupal.domain.DAO;
import com.example.proyectogrupal.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Comienza la transacción.
            transaction = session.beginTransaction();

            // Actualiza el pedido en la Base de Datos.
            session.save(data);

            // Commit de la transacción.
            transaction.commit();
        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir durante la transacción.
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return data;
    }

    @Override
    public void update(Empresa data) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Comienza la transacción.
            transaction = session.beginTransaction();

            // Actualiza el pedido en la Base de Datos.
            session.update(data);

            // Commit de la transacción.
            transaction.commit();
        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir durante la transacción.
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }


    }

    @Override
    public void delete(Empresa data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Empresa empresa = session.get(Empresa.class, data.getID_Empresa());
            session.remove(empresa);
        });

    }
}
