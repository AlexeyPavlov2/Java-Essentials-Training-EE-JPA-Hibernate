package service;

import db.HibernateUtil;
import entity.Employee;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeService {

    public void add(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(employee);
        tx.commit();
        session.close();
    }
    
    public List<Employee> getAll() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String sql = "SELECT * FROM EMPLOYEE";
        Query query = session.createNativeQuery(sql).addEntity(Employee.class);
        List<Employee> employeeList = query.list();
        tx.commit();
        session.close();
        return employeeList;
    }
    
    public Employee getById(Long id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String sql = "SELECT * FROM EMPLOYEE WHERE ID = :id";
        Query query = session.createNativeQuery(sql).addEntity(Employee.class);
        query.setParameter("id", id);
        Employee employee = (Employee) query.getSingleResult();
        tx.commit();
        session.close();
        return employee;
    }
    
    public void update(Employee employee) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(employee);
        tx.commit();
        session.close();
    }

    public void remove(Employee employee) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.remove(employee);
        tx.commit();
        session.close();
    }
    
    public List<Employee> getEmployeesByProjectID(Long projectid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String sql = "select EMPLOYEE.id, EMPLOYEE.BIRTHDAY, EMPLOYEE.FIRST_NAME, EMPLOYEE.LAST_NAME, EMPLOYEE.address_id FROM EMPLOYEE JOIN EMPL_PROJ ON " +
                " EMPL_PROJ.EMPLOYEE_ID=EMPLOYEE.id WHERE EMPL_PROJ.PROJECT_ID=:id";

        /*Query employees = session.createNativeQuery(sql);
        employees.setParameter("id", projectid);
        List<Object[]> empl = employees.getResultList();
        empl..forEach(a->System.out.println(a[0] + " " + a[1] 
                + " " + a[2] + " " + a[3] + " " + a[4]));
        */
        Query query = session.createNativeQuery(sql).addEntity(Employee.class);
        query.setParameter("id", projectid);
        List<Employee> listEmployees = query.list();
        tx.commit();
        session.close();
        return listEmployees;

    }

}
