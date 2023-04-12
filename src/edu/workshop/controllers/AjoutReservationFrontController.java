/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import edu.workshop.services.Evenement1CRUD;
import edu.workshop.services.Reservation1CRUD;
import edu.worshop.model.Evenement;
import edu.worshop.model.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    @FXML
    Evenement E;
    private ChoiceBox<Integer> Evenement_idRfx;
     private final String[] Evenement_idRfxVariable = {"E.getId()"};

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
        
         int evenement_id = Evenement_idRfx.getValue();
    
    
    {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("are you sure to add this reservation ?");
Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)  {


              Reservation R = new Reservation(AffichageEvenementBackController.E.getId(), paiement, nom, prenom, adresse, telephone);
                    Reservation1CRUD res1 = new Reservation1CRUD();
                    res1.ajouterReservation(R);
                    showAlert("Reservation added successfully");
    }
    }

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
     private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    }



 
    

    

