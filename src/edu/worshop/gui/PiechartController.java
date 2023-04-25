/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.gui;


import edu.workshop.controllers.AfficherCategorieBackController;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javax.activation.DataSource;
import edu.worshop.utils.MyConnection;

//import utils.DataSource;
/**
 *
 * @author lenovo
 */
public class PiechartController {
     @FXML
    private PieChart piechart;
    private Statement st;
    private ResultSet rs;
   // private Connection cnx;
        Connection conn = MyConnection.getInstance().getConn();
    
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    //@Override
    public void initialize(URL url, ResourceBundle rb) {
    
        stat();
    }    
     private void stat()
    {
          
           
      try {
           
          String query = "SELECT COUNT(*),categorie FROM Produit GROUP BY categorie" ;
       
             PreparedStatement PreparedStatement = conn.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("categorie"),rs.getInt("COUNT(*)")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        piechart.setTitle("**Statistiques nombres des catégories**");
        piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);
    
    }
    

}
