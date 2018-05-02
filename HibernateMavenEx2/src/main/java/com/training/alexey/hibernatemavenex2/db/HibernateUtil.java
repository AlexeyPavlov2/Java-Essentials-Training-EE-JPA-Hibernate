package com.training.alexey.hibernatemavenex2.db;

import com.training.alexey.hibernatemavenex2.entity.Exam;
import com.training.alexey.hibernatemavenex2.entity.Student;
import com.training.alexey.hibernatemavenex2.entity.StudentAddress;
import com.training.alexey.hibernatemavenex2.entity.Subject;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    //declare a session factory
    private static final SessionFactory concreteSessionFactory;
    //put all code in static block so that it is initialized at the time of class load
    static {
	try {
		Properties prop= new Properties();
                //provide the required properties
                prop.setProperty("hibernate.connection.url",  "jdbc:mysql://localhost:3306/exams?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
	        prop.setProperty("hibernate.connection.username", "root");
		prop.setProperty("hibernate.connection.password", "root");
                prop.setProperty("dialect", "org.hibernate.dialect.MySQL5Dialect");
                prop.setProperty("hibernate.current_session_context_class", "thread");
                //prop.setProperty("hibernate.hbm2ddl.auto","create");
                prop.setProperty("hibernate.hbm2ddl.auto","update");
                
                //create a configuration
		Configuration config = new Configuration();
		//provide all properties to this configuration
		config.setProperties(prop);
                //add classes which are mapped to database tables.
		config.addAnnotatedClass(Student.class);
                config.addAnnotatedClass(Exam.class);
                config.addAnnotatedClass(StudentAddress.class);
                config.addAnnotatedClass(Subject.class);
		//initialize session factory
		concreteSessionFactory = config.buildSessionFactory();
	} catch (Throwable ex) {
		throw new ExceptionInInitializerError(ex);
	}
  }
  /**
  * Return session for every database transaction from this static method
  */
  public static Session getSession() throws HibernateException {
		return concreteSessionFactory.openSession();
	}
  
  public static void shutdown() {
        concreteSessionFactory.close();
    }
}