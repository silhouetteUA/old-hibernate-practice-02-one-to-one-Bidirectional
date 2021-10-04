package com.company.demo;

import com.company.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Creating a new Student ...");
            Student student1 = new Student("Nikolay", "Kilka", "nekiyniko@gmail.com");
            Student student2 = new Student("Mihail", "Molochkin", "miha@gmail.com");
            System.out.println("Begin transaction ...");
            session.beginTransaction();
            System.out.println("Saving student to DB");
            session.save(student1);
            session.save(student2);
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
