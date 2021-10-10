package com.company.demo;

import com.company.entities.Instructor;
import com.company.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .addAnnotatedClass(Instructor.class)
                                            .addAnnotatedClass(InstructorDetail.class)
                                            .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {

            //create instructor and instructorDetail object and link them up
            //Instructor tempInstructor = new Instructor("Evgeniy","Dmitriev","edmitr@lifecell.com.ua");
            Instructor tempInstructor2 = new Instructor("Anna","Ivanenko","anniv@ciklum.com");
            //InstructorDetail instructorDetail = new InstructorDetail("@youtubeChannelEDMITR", "Football");
            InstructorDetail instructorDetail2 = new InstructorDetail("@youtubeChannelANNIV", "Tennis");
            //tempInstructor.setInstructorDetailId(instructorDetail);
            tempInstructor2.setInstructorDetailId(instructorDetail2);
            //save instructor (will save details as well, cascadeType.All)
            //System.out.println("Saving instructor1: " +tempInstructor.toString());
            System.out.println("Saving instructor2: " +tempInstructor2.toString());
            session.beginTransaction();
            session.save(tempInstructor2);
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }

    }
}
