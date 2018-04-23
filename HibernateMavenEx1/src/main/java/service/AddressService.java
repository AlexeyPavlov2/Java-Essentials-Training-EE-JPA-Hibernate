package service;

import db.HibernateUtil;
import entity.Address;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class AddressService {

    public void add(Address address) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(address);
        tx.commit();
        session.close();
    }

    public List<Address> getAll() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Address> addressList = session.createCriteria(Address.class).list();
        tx.commit();
        session.close();
        return addressList;
    }

    public Address getByIdWithCriteria(Long id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Address> address = session.createCriteria(Address.class)
                .add(Restrictions.eq("id", id))
                .list();
        tx.commit();
        session.close();
        return address.get(0);
    }

    public Address getByIdWithNativeSQL(Long id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String sql = "SELECT * FROM ADDRESS WHERE ID = :id";
        Query query = session.createNativeQuery(sql).addEntity(Address.class);
        query.setParameter("id", id);
        Address address = (Address) query.getSingleResult();
        tx.commit();
        session.close();
        return address;
    }

    public Address getByIdWithJPACriteria(Long id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Address> query = builder.createQuery(Address.class);
        Root<Address> root = query.from(Address.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query<Address> q = session.createQuery(query);
        Address address = q.getSingleResult();
        tx.commit();
        session.close();
        return address;
    }
    
    public void update(Address address) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(address);
        tx.commit();
        session.close();
    }
    
    public void remove(Address address) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.remove(address);
        tx.commit();
        session.close();
    }
}
