package service;

import db.HibernateUtil;
import entity.Country;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CountryInfo {

    public CountryInfo() {

    }

    public Country getCountryInfoByCodeTest(String code) {
        Country country = new Country(0, "Moscow", "ru", "Russia", 123L, 234L, BigDecimal.valueOf(77.8), 98878, null);
        return country;
    }

    public Country getCountryInfoByCode(String code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Country country = null;
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Country.class);

        List countryList = session.createCriteria(Country.class)
                .add(Restrictions.eq("code", code))
                .list();
        session.getTransaction().commit();
        if (!countryList.isEmpty())
            country = (Country) countryList.get(0);

        return country;
    }

}
