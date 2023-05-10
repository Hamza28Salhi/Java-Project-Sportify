/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;



import edu.workshop.services.AbonnementService;
import edu.worshop.model.Abonnement;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;


/**
 * FXML Controller class
 *

 */
public class ShowAbonnementController implements Initializable {

    @FXML
    private ListView<Abonnement> listView;
   
    ObservableList<Abonnement> data;
    
    public static int idE ;
    
    AbonnementService cs = new AbonnementService();

   
    @FXML
    private Button partager;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            data = (ObservableList<Abonnement>) cs.getAllAbonnement();   
            listView.setItems(data);
            listView.setCellFactory((ListView<Abonnement> param) -> new ListViewAbonnment());
            
            
            // TODO
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    




    @FXML
    private void handleClose(ActionEvent event) {
    }




    }

    

