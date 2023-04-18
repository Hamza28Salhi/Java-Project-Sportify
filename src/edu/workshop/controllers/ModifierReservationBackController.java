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
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
    
    @FXML
    private Label labelMtel;
    private boolean verificationNumero;
    
   

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
        int minLength1 = 4;
        
        if (nom.isEmpty()) {
            showAlert("le nom est vide ");
         }else if (!nom.matches(".*([a-zA-Z])+")) {
            showAlert("le nom doit être alphabétique ");
         }else if (nom.matches(".*([a-zA-Z])\\1{2,}.*")) {
            showAlert("La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement.");
             }else if (nom.length() < minLength1) {
            showAlert("Le type doit contenir au moins " + minLength1 + " caractères"); 
             }
             
             else  if (prenom.isEmpty()) {
            showAlert("le prénom est vide ");
         }else if (!prenom.matches(".*([a-zA-Z])+")) {
            showAlert("le prénom doit être alphabétique ");
         }else if (prenom.matches(".*([a-zA-Z])\\1{2,}.*")) {
            showAlert("La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement.");
             }else if (prenom.length() < minLength1) {
            showAlert("Le prénom doit contenir au moins " + minLength1 + " caractères"); 
             
             }else if (verifEmail(AdresseMfx)){
             showAlert("l'email est invalide!");
             }/*else if (verificationNumero) {
             showAlert("Le numero est invalide");
               
             }*/else{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("are you sure to add this event ?");
Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
        Reservation res = new Reservation(AfficherReservationBackController.R.getId(), AfficherReservationBackController.R.getEvenement_id(), paiement, nom, prenom, adresse, telephone);
                inter.modifierReservation(res);
                showAlert("la réservation a été modifié avec succès ");}
           
                 try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AfficherReservationBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
 
                
                

    }
}


private boolean verifEmail(TextField chaine) {
        String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(email_pattern);
        Matcher matcher = pattern.matcher(chaine.getText().trim());

        if (((Matcher) matcher).matches()) {       //if   matcher ne contient pas la format
            return false;

           // verificationUserEmail = true;

        } else {
            return true;
            // JOptionPane.showMessageDialog(null, "Email Format invalide");
            //verificationUserEmail = false;

        }

 /*@FXML
    private void veriftel(KeyEvent event) {
    if (TelephoneMfx.getText().trim().length() == 8) {
        int nbChar = 0;
        for (int i = 1; i < TelephoneMfx.getText().trim().length(); i++) {
            char ch = TelephoneMfx.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
          
        }

        if (nbChar == 0) {
            labelMtel.setText("number valide");
            //labeltel.setTextFill(Color.GREEN);

            verificationNumero = false;
        } else {
            labelMtel.setText("invalide number \n"
                    + " Il exist des char");
            verificationNumero = true;

        }

    } else {
        labelMtel.setText("Il faut 8 chiffres");
       verificationNumero = true;*/


}}
   
        
    
    

