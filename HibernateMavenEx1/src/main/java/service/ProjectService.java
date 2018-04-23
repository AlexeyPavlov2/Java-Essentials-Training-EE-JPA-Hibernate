package service;

import db.HibernateUtil;
import entity.Project;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProjectService {

    public void add(Project project) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(project);
        tx.commit();
        session.close();
    }

    public List<Project> getAll() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String sql = "SELECT * FROM PROJECT";
        Query query = session.createNativeQuery(sql).addEntity(Project.class);
        List<Project> projectList = query.list();
        tx.commit();
        session.close();
        return projectList;
    }

    public Project getById(Long id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String sql = "SELECT * FROM PROJECT WHERE ID = :id";
        Query query = session.createNativeQuery(sql).addEntity(Project.class);
        query.setParameter("id", id);
        Project project = (Project) query.getSingleResult();
        tx.commit();
        session.close();
        return project;
    }

    public void update(Project project) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(project);
        tx.commit();
        session.close();
    }

    public void remove(Project project) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.remove(project);
        tx.commit();
        session.close();
    }
}