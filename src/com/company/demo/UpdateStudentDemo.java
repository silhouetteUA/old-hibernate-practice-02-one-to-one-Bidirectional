package com.company.demo;

import com.company.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            int studentid = 4;
            session.beginTransaction();
            Student student = session.get(Student.class, studentid);
            System.out.println("Get completed for: "+student.toString());
            System.out.println("Updating name");
            student.setFirstName("ScoobyDooby");
            session.getTransaction().commit();

            //MASS UPDATE email field for ALL
            System.out.println("Starting mass email update!");
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("update Student set email = 'foobar1@gmail.com'").executeUpdate();
            System.out.println("Done");

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }

    }
}
