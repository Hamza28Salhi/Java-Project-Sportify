/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;
import edu.workshop.services.Equipe1CRUD;
import edu.workshop.services.Matches1CRUD;
import edu.worshop.model.Equipe;
import edu.worshop.model.Matches;
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


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author Ace River
 */
public class AjoutMatchesBackController implements Initializable {
      @FXML
    private TextField NomMatchesfx;
    @FXML
    private TextField StadeMatchesfx;
    @FXML
    private TextField ScoreMatchesfx;
    @FXML
    private TextField NomEquipeIdMatchesfx;
    @FXML
    private DatePicker DateMatchesfx;
    

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

      @FXML
    private void AjouterMatchesBack(ActionEvent event) {
           if (NomMatchesfx.getText().isEmpty() || StadeMatchesfx.getText().isEmpty() || ScoreMatchesfx.getText().isEmpty() || NomEquipeIdMatchesfx.getText().isEmpty() || DateMatchesfx.getValue() == null) {
        showAlert("Please fill in all fields.");
        return;
    }
        //String Equipe_id = Equipe_idRfx.getValue();
        Date date = Date.valueOf(DateMatchesfx.getValue());
     String nom = NomMatchesfx.getText().trim();
if (nom.isEmpty() || !Character.isUpperCase(nom.charAt(0)) || !nom.matches("[a-zA-Z ]+")) {
    showAlert("Please enter a valid value for Nom, starting with an uppercase letter and without symbols.");
    return;
}
nom = Character.toUpperCase(nom.charAt(0)) + nom.substring(1);

        String stade = StadeMatchesfx.getText();
        String score = ScoreMatchesfx.getText();
        int nomEquipeid;
try {
    nomEquipeid = Integer.parseInt(NomEquipeIdMatchesfx.getText());
} catch (NumberFormatException e) {
    showAlert("Nom Equipe ID should be a number.");
    return;
}

        
         
    
    
    {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("are you sure to add this Matches ?");
Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)  {


                     
                    
                    
                    
              Matches R = new Matches( nom, stade,date, score,nomEquipeid);
                    Matches1CRUD res1 = new Matches1CRUD();
                    res1.ajouterMatches(R);
                    System.out.println(AffichageEquipeBackController.E.getId());
                    showAlert("Matches added successfully");
                    
    }
          
                   try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageMatchesBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

/*try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageEquipeBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);
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