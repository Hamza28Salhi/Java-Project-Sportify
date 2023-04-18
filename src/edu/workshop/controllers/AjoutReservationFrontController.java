/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import edu.workshop.services.Emailsender;
import edu.workshop.services.Evenement1CRUD;
import edu.workshop.services.Reservation1CRUD;
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
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author HOUYEM
 */
public class AjoutReservationFrontController implements Initializable {

    @FXML
    private ChoiceBox<String> PaiementRfx;
    @FXML
    private TextField TelephoneRfx;
    @FXML
    private TextField AdresseRfx;
    @FXML
    private TextField PrenomRfx;
    @FXML
    private TextField NomRfx;
     private final String[] PaiementRfxVariable = {"Par chèque", "Par virement", "Par espèces"};
    //private TextField evenement_idfx;
    Evenement E;
    static int evenement_id;
    @FXML
    private Label labeltel;
    private boolean verificationNumero;
    
    
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PaiementRfx.getItems().addAll(PaiementRfxVariable);
        //Evenement_idRfx.getItems().addAll(Evenement_idRfxVariable);
    }    

    @FXML
    private void AjoutReservationFront(ActionEvent event) {
        //String evenement_id = Evenement_idRfx.getValue();
        String paiement = PaiementRfx.getValue();
        String telephone = TelephoneRfx.getText();
        String adresse = AdresseRfx.getText();
        String prenom = PrenomRfx.getText();
        String nom = NomRfx.getText();
        
        int minLength1 = 4;
        
        if (nom.isEmpty()) {
            //showAlert("le nom est vide ");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "le nom est vide!", ButtonType.OK);
        alert.showAndWait();
         }else if (!nom.matches(".*([a-zA-Z])+")) {
            //showAlert("le nom doit être alphabétique ");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "le nom doit être alphabétique!", ButtonType.OK);
        alert.showAndWait();
         }else if (nom.matches(".*([a-zA-Z])\\1{2,}.*")) {
            //showAlert("La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement.");
         Alert alert = new Alert(Alert.AlertType.INFORMATION, "La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement!", ButtonType.OK);
        alert.showAndWait();    
         }else if (nom.length() < minLength1) {
            //showAlert("Le type doit contenir au moins " + minLength1 + " caractères"); 
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le type doit contenir au moins 4  caractères!", ButtonType.OK);
        alert.showAndWait();
             }
             
             else  if (prenom.isEmpty()) {
            //showAlert("le prénom est vide ");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "le prénom est vide!", ButtonType.OK);
        alert.showAndWait();
         }else if (!prenom.matches(".*([a-zA-Z])+")) {
            //showAlert("le prénom doit être alphabétique ");
         Alert alert = new Alert(Alert.AlertType.INFORMATION, "le prénom doit être alphabétique!", ButtonType.OK);
        alert.showAndWait();
         }else if (prenom.matches(".*([a-zA-Z])\\1{2,}.*")) {
            //showAlert("La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement.");
         Alert alert = new Alert(Alert.AlertType.INFORMATION, "La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement!", ButtonType.OK);
        alert.showAndWait();    
         }else if (prenom.length() < minLength1) {
            //showAlert("Le prénom doit contenir au moins " + minLength1 + " caractères"); 
             Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le prénom doit contenir au moins 4 caractères!", ButtonType.OK);
        alert.showAndWait();
             }else if (verifEmail(AdresseRfx)){
             //showAlert("l'email est invalide!");
             Alert alert = new Alert(Alert.AlertType.INFORMATION, "l'email est invalide!", ButtonType.OK);
        alert.showAndWait();
             }/*else if (verificationNumero) {
             //showAlert("Le numero est invalide");
             Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le numero est invalide!", ButtonType.OK);
        alert.showAndWait();
               
             }*/else{
        
         
    
    
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("êtes-vous sûr d'ajouter cette réservation  ?");
Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)  {

                    
              Reservation R = new Reservation(AffichageEvenementBackController.E.getId(), paiement, nom, prenom, adresse, telephone);
                    Reservation1CRUD res1 = new Reservation1CRUD();
                    res1.ajouterReservation(R);
                    //showAlert("Réservation ajoutée avec succès");
                     System.out.println("Réservation ajoutée avec succès");
                      
                     
                     String message = "Dear [Client's Name],\n"
                        + "\n"
                        + "I am writing this email to confirm your location reservation for the following details:\n"
                        + "\n"
                        + "nom  : " + nom  + "\n"
                        + "prenom  : " + prenom + "\n"
                        + "adresse  : " + adresse  + "\n"
                        + "telephone  : " + telephone + "\n"
                        + "paiement  : " + paiement  + "\n"
                       
                        //+ "We are pleased to inform you that your reservation has been successfully processed, and we have reserved the required number of seats for you. Your confirmation number is [Enter confirmation number].\n"
                        + "\n";

                Emailsender.sendEmail_add("houyem.kaaniche@esprit.tn", message);
    

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
                }}
    /* private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }*/
    // Show a success message
       // Alert alert = new Alert(Alert.AlertType.INFORMATION, "thank you for joining Sportify!", ButtonType.OK);
       // alert.showAndWait();
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

        }}
      /* @FXML
    private void veriftel(KeyEvent event) {
    if (TelephoneRfx.getText().trim().length() == 8) {
        int nbChar = 0;
        for (int i = 1; i < TelephoneRfx.getText().trim().length(); i++) {
            char ch = TelephoneRfx.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
          
        }

        if (nbChar == 0) {
            labeltel.setText("number valide");
            //labeltel.setTextFill(Color.GREEN);

            verificationNumero = false;
        } else {
            labeltel.setText("invalide number \n"
                    + " Il exist des char");
            verificationNumero = true;

        }

    } else {
        labeltel.setText("Il faut 8 chiffres");
       verificationNumero = true;
    }
}*/
    
        
}



 
    

    

