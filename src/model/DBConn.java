package model;

import java.sql.*;

public class DBConn {

private String url = "jdbc:mysql://localhost:3306/javaswing";
private String driver = "com.mysql.jdbc.Driver";
private String userName = "root";
private String password = "k085711233";
private java.sql.Connection con = null;

private void getConnection() {

     try {
         Class.forName(driver);
         if(con == null){
             con = DriverManager.getConnection(url,userName,password);
         }
         System.out.print("Connection establishedd");
     }catch (Exception e) {
         System.out.print("Error : " +e.getMessage());
    }
}

/**for desktop application */
public static void main(String[] arg){

    DBConn con = new DBConn();
    con.getConnection();
}
}