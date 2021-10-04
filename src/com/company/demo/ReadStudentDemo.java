package com.company.demo;

import com.company.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Creating a new Student ...");
            Student student = new Student("NewName", "NewLastName", "newemail@gmail.com");
            System.out.println("Begin transaction ...");
            session.beginTransaction();
            System.out.println("Saving student to DB: " + student.toString());
            session.save(student);
            System.out.println("After save: "+student.toString());
            session.getTransaction().commit();
            System.out.println("After commit: "+student.toString());
            System.out.println("Done!");


            //ready student using primary key
            session=sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Getting student with ID: "+student.getId());
            Student myStudent = session.get(Student.class, student.getId());
            session.getTransaction().commit();
            System.out.println("Done: "  +myStudent.toString());
        } finally {
            sessionFactory.close();
        }

    }
}
