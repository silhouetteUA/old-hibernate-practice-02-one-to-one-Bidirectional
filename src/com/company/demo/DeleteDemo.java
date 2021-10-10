package com.company.demo;

import com.company.entities.Instructor;
import com.company.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .addAnnotatedClass(Instructor.class)
                                            .addAnnotatedClass(InstructorDetail.class)
                                            .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {

            session.beginTransaction();
            //Delete the instructor  JUST in Instructor Table
            //session.createQuery("delete  Instructor where id = '1'").executeUpdate();


            //Delete using CASCADE -> Instructor + InstructorDetails
            int id = 2;
            Instructor tempInstructor = session.get(Instructor.class, id);
            System.out.println("Got: "+tempInstructor.toString());
            session.delete(tempInstructor);
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }

    }
}
