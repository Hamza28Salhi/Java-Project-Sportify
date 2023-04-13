/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import edu.workshop.services.Evenement1CRUD;
import edu.workshop.services.Reservation1CRUD;
import edu.worshop.interfaces.EvenementCRUD;
import edu.worshop.interfaces.ReservationCRUD;
import edu.worshop.model.Evenement;
import edu.worshop.model.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HOUYEM
 */
public class ModifierReservationBackController implements Initializable {

    @FXML
    private TextField NomMfx;
    @FXML
    private TextField PrenomMfx;
    @FXML
    private TextField AdresseMfx;
    @FXML
    private TextField TelephoneMfx;
    @FXML
    private ChoiceBox<String> PaiementMfx;
    private final String[] PaiementMfxVariable = {"Par chèque", "Par virement", "Par espèces"};
    static int id;
    static int evenement_id;
    static String paiement;
    static String nom;
    static String prenom;
    static String adresse;
    static String telephone;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PaiementMfx.getItems().addAll(PaiementMfxVariable);
        NomMfx.setText(String.valueOf(AfficherReservationBackController.R.getNom()));
        PrenomMfx.setText(String.valueOf(AfficherReservationBackController.R.getPrenom()));
        AdresseMfx.setText(String.valueOf(AfficherReservationBackController.R.getAdresse()));
        TelephoneMfx.setText(String.valueOf(AfficherReservationBackController.R.getTelephone()));
        PaiementMfx.setValue(String.valueOf(AfficherReservationBackController.R.getPaiement()));
        
        // TODO
    }  
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void ModifierReservationBack(ActionEvent event) {
        ReservationCRUD inter = new Reservation1CRUD();
        int id;
        String nom = NomMfx.getText();
        String prenom = PrenomMfx.getText();
        String adresse = AdresseMfx.getText();
        String telephone = TelephoneMfx.getText();
        String paiement = PaiementMfx.getValue();
        System.out.println(AfficherReservationBackController.R.getPaiement());
        
        Reservation res = new Reservation(AfficherReservationBackController.R.getId(), AfficherReservationBackController.R.getEvenement_id(), paiement, nom, prenom, adresse, telephone);
                inter.modifierReservation(res);
           
                 /*try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageEvenementBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }*/
 
                
                

    }
    
        
    }
    

