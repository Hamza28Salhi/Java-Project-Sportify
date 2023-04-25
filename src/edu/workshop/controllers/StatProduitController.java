/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;





import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
//import utils.DataSource;
import edu.worshop.utils.MyConnection;
import javax.sql.DataSource;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class StatProduitController implements Initializable {
      @FXML
     private PieChart piechart;
    private Statement st;
    private ResultSet rs;
    Connection conn = MyConnection.getInstance().getConn();
  //  private Connection cnx;
        ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // Connection cnx=DataSource.getInstance().getCnx();
        // Connection conn = MyConnection.getInstance().getConn();
        stat();
        // TODO
        // TODO
    }    
      private void stat()
    {
          
           
      try {
           
          String query = "SELECT COUNT(*),categorie FROM produit GROUP BY categorie" ;
       
             PreparedStatement PreparedStatement = conn.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("categorie"),rs.getInt("COUNT(*)")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(AfficherProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        piechart.setTitle("**Statistiques nombres des catégories**");
        piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);
    
    }
}
