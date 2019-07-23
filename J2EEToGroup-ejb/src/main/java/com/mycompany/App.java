/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Luca
 */
public class App {
 
        private final String url = "jdbc:postgresql://localhost:5432/postgres";
        private final String user = "postgres";
        private final String password = "admin";

        /**
         * Connect to the PostgreSQL database
         *
         * @return a Connection object
         * @throws java.sql.SQLException
         */
        public Connection connect() throws SQLException {
            return DriverManager.getConnection(url, user, password);
        }
        
        
        public String getAllUser() { 
            String SQL = "SELECT * FROM \"public\".appuser WHERE USERNAME = ?";
            String result = "Iniziale";
          System.out.println("asdasd");
            try (
                
                    
                Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setString(1,"dbuser1");
                ResultSet rs = pstmt.executeQuery();          
                while (rs.next()) {
                    result += rs.getString("useremail");
                }
                return result;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }            
            return "zero";
        }
    }
