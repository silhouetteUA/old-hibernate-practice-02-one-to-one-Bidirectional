package com.company.demo;

import com.company.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            //query studens
            List<Student> theStudents = session.createQuery("from Student").list();
            //display students
            displayStudents(theStudents);

            //query students with last name
            theStudents = session.createQuery("from Student x where x.lastName = 'Dmitriev'").list();
            System.out.println("displaying only with last name = Dmitriev");
            displayStudents(theStudents);

            //query with OR
            theStudents = session.createQuery("from Student x where x.lastName = 'Dmitriev' OR x.firstName = 'NewName'OR x.email LIKE 'miha%'").list();
            System.out.println("last name = Dmitriev or firstName= NewName or email LIKE miha%");
            displayStudents(theStudents);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }

    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student element : theStudents)  {
            System.out.println(element.toString());
        }
    }
}
