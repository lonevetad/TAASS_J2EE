/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luca
 */
public class NewServlet extends HttpServlet {

    public static String idUser = "";
    public static AppUser userLogged = null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        App app = new App();
        //System.out.println(request.getAttribute("id"));
        
        System.out.println("Entra in servlet");        
        if(request != null){
            System.out.println("Daje: " +request.getParameter("id"));
            idUser = request.getParameter("id");
            userLogged = app.loadUser(idUser);
        }
        
        //-------------------------------------------------------------------
        

      

        
        //-------------------------------------------------------------------
        //String response1 = app.getAllUser2();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            

           

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");    
            out.println("<div id=\"appUserPage\">" +               
                            "<div class=\"container\">"+
                                "<div class=\"container-fluid user__header\">"+
                                    "<img src=\"https://source.unsplash.com/random\" alt=\"\">" +
                                        "<div class=\"user__header__name\"><h1>" +userLogged.getUserName() +"</h1></div>"+
                                "</div>"+
                                "<div class=\"user__body__info\">"+
                                    "<div class=\"row\">" +
                                        "<div class=\"col-md-3\"></div>"+
                                        "<div class=\"col-md-3\">" +
                                            "<i class=\"far fa-calendar-alt\"></i>" + userLogged.getuserEmail()+
                                        "</div>"+
                                        "<div class=\"col-md-3\">"+
                                            "<i class=\"fas fa-users\"></i>Iscritto a 3 gruppi"+
                                        "</div>"+
                                        "<div class=\"col-md-3\"></div>"+
                                    "</div>"+
                                "</div>"+
                                "<hr>"+
                                "<div class=\"container-fluid user__body\">"+
                                    "<div class=\"user__body__description\"><h3>Descrizione</h3>"+

                                        "<textarea id=\"descriptionInput\"" +
                                                  "cols=\"50\""+
                                                  "rows=\"10\"" +
                                                  "placeholder=\"Description\""+                                         
                                                  "class=\"descriptionInput\""+
                                                  "value=\"Piccola descrizione \""+
                                        "></textarea>"+

                                        "<button class=\"btn btn-default\" id=\"descriptionButton\" >"+
                                            "Cambia Descrizione"+
                                        "</button>"+

                                    "</div>"+

                                "</div>"+

                                "<hr>"+
                                
                            "</div>"+
                        "</div>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("<h1> " + userLogged.getUserName()+ "</h1>");
            out.println("<h1> " + userLogged.getuserEmail()+ "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            
            
            
            /* FUNZIONANTE
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");    
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("<h1> " + userLogged.getUserName()+ "</h1>");
            out.println("<h1> " + userLogged.getuserEmail()+ "</h1>");
            out.println("</body>");
            out.println("</html>");*/
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);         
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public class App {
 
        private final String url = "jdbc:postgresql://localhost:5432/postgres";
        private final String user = "postgres";
        private final String password = "admin";
        String dbDriver = "org.postgresql.Driver";

        /**
         * Connect to the PostgreSQL database
         *
         * @return a Connection object
         * @throws java.sql.SQLException
         */
        public Connection connect() throws SQLException {
            return DriverManager.getConnection(url, user, password);
        }
        
        
        public AppUser loadUser(String idUser) throws ClassNotFoundException{           
      
            //------------------------------------------------------------------ 
            try{
                Class.forName(dbDriver);
                Connection conn = this.connect();
                System.out.println("Got Connection");
     
                
                PreparedStatement stat = conn.prepareStatement("select * from appuser where  userid = ?");
                
                Long x = Long.parseLong(idUser);
                System.out.println(x);
                stat.setLong(1, x);
                
                ResultSet rs = stat.executeQuery();
                userLogged = new AppUser();
                while (rs.next()) {                  
                    userLogged.setUserName(rs.getString("username"));
                    userLogged.setUserId(x);
                    userLogged.setuserEmail(rs.getString("useremail"));
                    return userLogged;
                }
                }catch(SQLException e){
                   e.printStackTrace();
                }               
         
        
            return null;
        }
        public String getAllUser() throws ClassNotFoundException {
      
            //------------------------------------------------------------------ 
            try{
                Class.forName(dbDriver);
                Connection conn = this.connect();
                System.out.println("Got Connection");
                Statement statement = conn.createStatement();
                String sql = "select * from appuser";
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    return rs.getString("username");
                }
                }catch(SQLException e){
                   e.printStackTrace();
                }               
            return "zero";
        }
        
               public String getAllUser2() throws ClassNotFoundException {
      
            //------------------------------------------------------------------ 
            try{
                Class.forName(dbDriver);
                Connection conn = this.connect();
                System.out.println("Got Connection");
     
                
                PreparedStatement stat = conn.prepareStatement("select * from appuser where username = ?");
                
                stat.setString(1, "dbuser1");
                
                ResultSet rs = stat.executeQuery();
                while (rs.next()) {
                    return rs.getString("username");
                }
                }catch(SQLException e){
                   e.printStackTrace();
                }               
            return "zero";
        }
    }
}


