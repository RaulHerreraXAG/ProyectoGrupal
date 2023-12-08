package com.example.proyectogrupal.actividad;

import com.example.proyectogrupal.domain.DAO;
import com.example.proyectogrupal.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
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
    public void delete(Actividad data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Actividad actividad = session.get(Actividad.class, data.getID_Actividad());
            session.remove(actividad);
        });

    }
    public int calcularTotalHoras(List<Actividad> horas){
        int totalHoras = 0;
        for (Actividad actividad : horas) {
            totalHoras += actividad.getHoras();
        }
        return totalHoras;
    }
}
