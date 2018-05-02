package com.training.alexey.hibernatemavenex2.main;

import com.training.alexey.hibernatemavenex2.db.HibernateUtil;
import com.training.alexey.hibernatemavenex2.entity.Student;
import com.training.alexey.hibernatemavenex2.reports.ExamReport;
import java.util.List;
import java.util.logging.Level;
import org.hibernate.Query;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {
        //Turning off hibernate logging console output
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query simpleQuery = session.createQuery("from Student");
        List<Student> simpleList = simpleQuery.getResultList();
        simpleList.forEach(e -> {
            List<ExamReport> list = getExamResultForStudentByName(e.getLastname(), e.getFirstname(), session);
            if (!list.isEmpty()) {
                System.out.println("Exams result for " + e.getFirstname() + " " + e.getLastname() + ":");
                list.forEach(v -> System.out.println(v));
            }
        });

        session.getTransaction().commit();
        session.close();
        HibernateUtil.shutdown();
    }

    public static List<ExamReport> getExamResultForStudentByName(String lastName, String firstName, Session session) {
        /*String hql = "select new com.training.alexey.hibernatemavenex2.reports.ExamReport(s.id, s.lastname, s.firstname, s.age, s.address.country, sj.name, e.grade) from Exam e, Student s, Subject sj "
                + "where e.student.id = s.id and e.subject.id = sj.id "
                + "and s.firstname = :firstname and s.lastname = :lastname";
        */
        
        //even shorter
        String hql = "select new com.training.alexey.hibernatemavenex2.reports.ExamReport(e.student.id, e.student.lastname, e.student.firstname, e.student.age, e.student.address.country, e.subject.name, e.grade) " 
                + "from Exam e where e.student.firstname = :firstname and e.student.lastname = :lastname";
        Query query = session.createQuery(hql);
        query.setParameter("firstname", firstName);
        query.setParameter("lastname", lastName);
        return query.getResultList();
    }

}
