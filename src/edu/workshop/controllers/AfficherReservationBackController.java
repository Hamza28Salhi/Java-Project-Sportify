/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import static edu.workshop.controllers.AffichageEvenementBackController.E;
import edu.workshop.services.Evenement1CRUD;
import edu.workshop.services.Reservation1CRUD;
import edu.worshop.interfaces.EvenementCRUD;
import edu.worshop.interfaces.ReservationCRUD;
import edu.worshop.model.Evenement;
import edu.worshop.model.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HOUYEM
 */
public class AfficherReservationBackController implements Initializable {

    @FXML
    private ListView<Reservation> AfficherReservationBackfx;
    static int evenement_id,numero;
static String paiement,nom,prenom,adresse;
static Reservation R = new Reservation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListView<Reservation> list1 = AfficherReservationBackfx;
ReservationCRUD inter = new Reservation1CRUD();
List<Reservation> list2 = inter.afficherReservation();
for (int i = 0; i < list2.size(); i++) {
    Reservation R = list2.get(i);
    list1.getItems().add(R); // add Evenement to ListView
}


        }

    @FXML
    private void ModifierReservationBack(ActionEvent event) {
         ListView<Reservation> list = AfficherReservationBackfx;
        ReservationCRUD inter = new Reservation1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        
  if (selectedIndex >=0){
            
        
        Reservation r = list.getSelectionModel().getSelectedItem();
 
        int id = r.getId();
        String nom = r.getNom();
        String prenom = r.getPrenom();
        String adresse = r.getAdresse();
        String telephone = r.getTelephone();
        String paiement = r.getPaiement();
        R=r;
      
        
        
        
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/ModifierReservationBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        }else{showAlert("Veuillez sélectionner une réservation à modifier.");
  }
    }

    private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void SupprimerReservationBack(ActionEvent event) {
         ListView<Reservation> list = (ListView<Reservation>) AfficherReservationBackfx;
    ReservationCRUD inter = new Reservation1CRUD();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Reservation R = list.getSelectionModel().getSelectedItem();
        inter.supprimerReservation(R.getId());
        list.getItems().remove(selectedIndex);
    } else {
        showAlert("Veuillez sélectionner une réservation à supprimer.");
    }
    }

 
       
    }
    
    

       
      
    

