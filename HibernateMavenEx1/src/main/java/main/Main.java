package main;

import db.HibernateUtil;
import entity.Address;
import entity.Employee;
import entity.Project;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import service.AddressService;
import service.EmployeeService;
import service.ProjectService;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Turning off hibernate logging console output
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);  
        
        System.out.println("Creating addreses in table ADDRESS...");
        AddressService as = new AddressService();
        ArrayList<Address> al = new ArrayList<>();
        al.add(new Address("USA", "Kenosha", "101 30th Avenue Kenosha", "53144"));
        al.add(new Address("USA", "Everett", "439 Seaway Boulevard", "98203"));
        al.add(new Address("Canada", "Edmonton", "104 Avenue", "AB T5J 4S2"));
        al.add(new Address("USA", "Fenton", "21 Copper Ave", "48430"));
        al.forEach(address -> as.add(address));
        System.out.println("Addreses created:");
        as.getAll().forEach(item -> System.out.println(item));
        
        
        System.out.println("Creating employees in table EMPLOYEE...");
        EmployeeService es = new EmployeeService();
        ArrayList<Employee> el = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(1928, Calendar.JANUARY, 1);
        el.add(new Employee("Paul", "Duncan", new Date(cal.getTime().getTime()), as.getByIdWithCriteria(1L)));
        cal.set(1968, Calendar.JULY, 30);
        el.add(new Employee("Simon", "Marlow", new Date(cal.getTime().getTime()), as.getByIdWithCriteria(2L)));
        cal.set(1999, Calendar.OCTOBER, 12);
        el.add(new Employee("Martin", "Cramer", new Date(cal.getTime().getTime()), as.getByIdWithCriteria(3L)));
        el.forEach(employee -> es.add(employee));
        System.out.println("Employees created:");
        es.getAll().forEach(employee -> System.out.println(employee));
        
        System.out.println("Creating projects in table PROJECT...");
        ProjectService ps = new ProjectService();
        
        Project p1 = new Project("Armed Capture of Mars");
        ps.add(p1);
        Project p2 = new Project("Falcon 25 To Jupiter");
        ps.add(p2);
        System.out.println("Projects created:");
        ps.getAll().forEach(project -> System.out.println(project));
        
        //Adding project info 
        Employee e1 = es.getById(1L);
        e1.getProjects().add(p1);
        e1.getProjects().add(p2);
        es.update(e1);
        
        Employee e2 = es.getById(2L);
        e2.getProjects().add(p2);
        es.update(e2);
        
        System.out.println("Searching employees for project id=2 using createNativeQuery:");
        es.getEmployeesByProjectID(2L).forEach(o->System.out.println(o));
                
        HibernateUtil.shutdown();
    }
    
}
