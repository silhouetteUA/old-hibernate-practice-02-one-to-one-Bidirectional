package com.company.demo;

import com.company.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {

            //delete APPROACH ONE (get + delete)
            int studentid = 4;
            session.beginTransaction();
            Student student = session.get(Student.class, studentid);
            session.delete(student);
            session.getTransaction().commit();

            //delete APPROACH TWO (HQL method executeQuery)
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete Student where id = '1'").executeUpdate();
            System.out.println("Done");

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }

    }
}
