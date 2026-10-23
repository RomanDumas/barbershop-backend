package com.roman.barber_shop;

import com.roman.barber_shop.persistence.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;


public class DatabaseBuilder {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        build();
    }

    private static void build() {
        SessionFactory sf = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Hairdresser.class)
                .addAnnotatedClass(Qualification.class)
                .addAnnotatedClass(Service.class)
                .addAnnotatedClass(Visit.class)
                .addAnnotatedClass(PaymentMethod.class)
                .addAnnotatedClass(Payment.class)
                .configure()
                .buildSessionFactory();

        Session session = sf.openSession();

        Transaction transaction = session.beginTransaction();

        transaction.commit();


        session.close();
        sf.close();
    }

}
