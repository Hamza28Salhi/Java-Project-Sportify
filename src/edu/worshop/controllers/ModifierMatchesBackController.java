/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.workshop.services.Matches1CRUD;
import edu.worshop.interfaces.MatchesCRUD;
import edu.worshop.model.Matches;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Ace River
 */
public class ModifierMatchesBackController implements Initializable {

    @FXML
    private TextField NomMatchesMfx;
    @FXML
    private TextField StadeMatchesMfx;
    @FXML
    private TextField ScoreMatchesMfx;
    private TextField NomMatchesIdMatchesMfx;
    @FXML
    private DatePicker DateMatchesMfx;
    static int id;
    static String nom;
    static String stade;
    static String score;
    static int nomEquipeid;
    static Date date;
    @FXML
    private TextField NomEquipeIdMatchesMfx;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          NomMatchesMfx.setText(String.valueOf(AffichageMatchesBackController.E.getNom()));
        StadeMatchesMfx.setText(String.valueOf(AffichageMatchesBackController.E.getStade()));
        ScoreMatchesMfx.setText(String.valueOf(AffichageMatchesBackController.E.getScore()));




      

       
    }    
        private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
private void ModifierMatchesBack(ActionEvent event) {
    // Retrieve the id of the Matches to update
    int id = AffichageMatchesBackController.E.getId();
    
    // Retrieve the new values from the text fields
    String nom = NomMatchesMfx.getText().trim();
    String stade = StadeMatchesMfx.getText().trim();
    String score = ScoreMatchesMfx.getText().trim();
    String nomEquipeIdText = NomEquipeIdMatchesMfx.getText().trim();
    
    // Input controls
    if (nom.isEmpty() || !Character.isUpperCase(nom.charAt(0))) {
        showAlert("Please enter a valid value for Nom, starting with an uppercase letter.");
        return;
    }
    if (!nom.matches("[a-zA-Z ]+")) {
        showAlert("Please enter a valid value for Nom, without any symbols or numbers.");
        return;
    }
    if (stade.isEmpty()) {
        showAlert("Please enter a value for Stade.");
        return;
    }
    if (score.isEmpty()) {
        showAlert("Please enter a value for Score.");
        return;
    }
    if (nomEquipeIdText.isEmpty()) {
        showAlert("Please enter a value for NomEquipeId.");
        return;
    }
    int nomEquipeid = 0;
    try {
        nomEquipeid = Integer.parseInt(nomEquipeIdText);
    } catch (NumberFormatException e) {
        showAlert("Please enter a valid integer value for NomEquipeId.");
        return;
    }
    
    // Convert the date from the DatePicker to a Date object
    Date date = Date.valueOf(DateMatchesMfx.getValue());
    
    // Create a new Matches object with the updated values
    Matches updatedMatches = new Matches(id, nom, stade, date, score, nomEquipeid);
    
    // Update the Matches in the database using the Matches1CRUD class
    Matches1CRUD matchesCRUD = new Matches1CRUD();
    matchesCRUD.modifierMatches(updatedMatches);

    // Display a success message
    showAlert("Matches updated successfully");
       try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageMatchesBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);
    }
}}
