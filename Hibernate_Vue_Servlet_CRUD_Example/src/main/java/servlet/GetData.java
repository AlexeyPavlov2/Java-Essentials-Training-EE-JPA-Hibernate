package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Customer;
import entity.CustomerWrapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CustomerService;




@WebServlet(name = "GetData", urlPatterns = {"/GetData"})
public class GetData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json, charset=UTF-8");
            response.setHeader("Cache-control", "no-cache, no-store");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "-1");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
            response.setHeader("Access-Control-Max-Age", "86400");

            Enumeration<String> parameterNames = request.getParameterNames();
            String param = parameterNames.nextElement();
            JsonParser jsonParser = new JsonParser();
            JsonObject requestObject = jsonParser.parse(param).getAsJsonObject();
            CustomerService cs;
            Gson gson;
            List<Customer> list;
            JsonObject innerObject;
            JsonObject jsonObject;
            JsonObject jo;
            Customer cus;
            switch ((String) requestObject.get("mode").getAsString()) {
                case "GetAllCustomer":
                    cs = new CustomerService();
                    list = cs.getAllCustomers();
                    gson = new Gson();
                    String res = gson.toJson(new CustomerWrapper(list)).toString();
                    out.write(res);
                    out.flush();
                break;
                case "GetCustomer":
                    cs = new CustomerService();
                    int id = requestObject.get("id").getAsInt();
                    Customer c = cs.getCustomer(id);
                    list = new ArrayList<Customer>();
                    list.add(c);
                    gson = new Gson();
                    res = gson.toJson(new CustomerWrapper(list)).toString();
                    System.out.println(res);
                    out.write(res);
                    out.flush();
                break;
                case "DeleteCustomer":
                    cs = new CustomerService();
                    int id1 = requestObject.get("id").getAsInt();
                    cs.deleteCustomer(id1);
                    innerObject = new JsonObject();
                    innerObject.addProperty("error", false);
                    jsonObject = new JsonObject();
                    jsonObject.add("deletedStatus", innerObject);
                    out.write(jsonObject.toString());
                    out.flush();
                break;
                case "UpdateCustomer":
                    cs = new CustomerService();
                    jo = requestObject.get("customer").getAsJsonObject();
                    cus = new Customer(jo.get("id").getAsInt(), jo.get("name").getAsString(), 
                            jo.get("address").getAsString(), jo.get("city").getAsString(), jo.get("phone").getAsString(), 
                            jo.get("mail").getAsString(), jo.get("site").getAsString(), jo.get("type").getAsString());
                    cs.updateCustomer(cus);
                    innerObject = new JsonObject();
                    innerObject.addProperty("error", false);
                    jsonObject = new JsonObject();
                    jsonObject.add("updaateStatus", innerObject);
                    out.write(jsonObject.toString());
                    out.flush();
                break;
                case "CreateCustomer":
                    cs = new CustomerService();
                    jo = requestObject.get("customer").getAsJsonObject();
                    cus = new Customer(jo.get("id").getAsInt(), jo.get("name").getAsString(), 
                            jo.get("address").getAsString(), jo.get("city").getAsString(), jo.get("phone").getAsString(), 
                            jo.get("mail").getAsString(), jo.get("site").getAsString(), jo.get("type").getAsString());
                    cus = cs.createCustomer(cus);
                    
                    innerObject = new JsonObject();
                    innerObject.addProperty("error", false);
                    
                    jsonObject = new JsonObject();
                    jsonObject.add("createStatus", innerObject);
                    
                    gson = new Gson();
                    JsonObject jsCustomer = (JsonObject) gson.toJsonTree(cus);
                    
                    jsonObject.add("customer", jsCustomer);
                    
                    out.write(jsonObject.toString());
                    
                    System.out.println(jsonObject.toString());
                    
                    out.flush();
                break;
            }
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
        return "GetData servlet";
    }

}
