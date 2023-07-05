package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {

    private Connection connect() {
        String url = "jdbc:sqlite:D:\\SoftwareDevelopment\\Projects\\Java\\boldare-java-project\\boldare.db";       
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll(){
        String sql = "SELECT * FROM coins";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getDouble("value") + "\t" +
                                   rs.getInt("amount"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

     public void update(CoinTypes coinTypes,int amount){
        String sql = "UPDATE coins SET amount=? WHERE value=?";
        
        try (Connection conn = this.connect();
             PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, amount);
            st.setDouble(2, coinTypes.getValue());
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

     public int getAmount(CoinTypes coinTypes){
        String sql = "SELECT amount FROM coins WHERE value="+coinTypes.getValue();
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
             return rs.getInt("amount");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}