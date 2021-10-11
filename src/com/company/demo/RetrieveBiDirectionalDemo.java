package com.company.demo;

import com.company.entities.Instructor;
import com.company.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RetrieveBiDirectionalDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .addAnnotatedClass(Instructor.class)
                                            .addAnnotatedClass(InstructorDetail.class)
                                            .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            int myId = 11;
            session.beginTransaction();
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, myId);
            System.out.println("InstructorDetails: " + instructorDetail);
            System.out.println("Instructor: " + instructorDetail.getInstructor().toString());
            session.getTransaction().commit();

        } catch (Exception ex)  {
            ex.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }
}
