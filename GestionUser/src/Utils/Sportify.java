/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author fahed
 */
public class Sportify {
final String URL ="jdbc:mysql://127.0.0.1:3306/sportify";
final String USER ="root";
final String PWD ="";
   private  Connection cnx;
 private  static Sportify instance;

private Sportify(){
    try {
        cnx = DriverManager.getConnection(URL, USER, PWD);
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());   
    }
    
}
public static Sportify getInstance (){
    if(instance == null){
        instance = new Sportify();
    }
    return instance;
}
public Connection getCnx(){
    return cnx;
}
}