package service;

import db.HibernateUtil;
import entity.Customer;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CustomerService {

    public List<Customer> getAllCustomers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Customer> list = null;
        list = session.createCriteria(Customer.class).list();
        session.getTransaction().commit();
        return list;
    }

    public Customer getCustomer(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Customer customer = null;
        List customers = session.createCriteria(Customer.class)
                .add(Restrictions.eq("id", id))
                .list();
        session.getTransaction().commit();
        if (!customers.isEmpty()) {
            customer = (Customer) customers.get(0);
        }
        return customer;
    }

    public void deleteCustomer(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Customer customer = (Customer) session.createCriteria(Customer.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
        session.delete(customer);
        session.getTransaction().commit();
    }

    public void updateCustomer(Customer c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hqlUpdate = "update Customer cus set cus.name = :name, cus.address = :address, cus.city = :city, cus.phone = :phone, cus.mail = :mail, cus.site = :site, cus.type = :type"
                + " where cus.id = :id";
        int updatedEntities = session.createQuery(hqlUpdate)
                .setInteger("id", c.getId())
                .setString("name", c.getName())
                .setString("address", c.getAddress())
                .setString("city", c.getCity())
                .setString("phone", c.getPhone())
                .setString("mail", c.getMail())
                .setString("site", c.getSite())
                .setString("type", c.getType())
                .executeUpdate();
        
        session.getTransaction().commit();
        
    }

    public Customer createCustomer(Customer c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
        return c;
    }

}
