package servlet;

import entity.Country;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONObject;
import service.CountryInfo;

public class GetInfo extends HttpServlet {

    public GetInfo() {
        super();
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String countryCode = request.getParameter("countryCode");
        System.out.println("Параметр: " + countryCode);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //response.setContentType("text/html");
        response.setContentType("application/json, charset=UTF-8");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        
        Country country = null;
        
        CountryInfo countryInfo = new CountryInfo();
        country = countryInfo.getCountryInfoByCode(countryCode);
        
        JSONObject obj = new JSONObject();
        if (country != null) {
            obj.put("capital", country.getCapital());
            obj.put("code", country.getCode());
            obj.put("name", country.getName());
            obj.put("area", country.getArea());
            obj.put("population", country.getPopulation());
            obj.put("lexpect", country.getLexpect());
            obj.put("gdp", country.getGdp());
            
        } else {
            
        obj.put("error", true);
        }
        
        String json = obj.toString();
        out.write(json);
        out.close();
          
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
