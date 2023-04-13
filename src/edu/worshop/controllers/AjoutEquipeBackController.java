/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.workshop.services.Equipe1CRUD;
import edu.worshop.interfaces.EquipeCRUD;
import edu.worshop.model.Equipe;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 * FXML Controller class
 *
 * @author Ace River
 */
public class AjoutEquipeBackController implements Initializable {
 
    @FXML
    private TextField NomEquipefx;
    @FXML
    private TextField JoueursEquipefx;
    @FXML
    private TextField ClassementEquipefx;
    @FXML
    private TextField EntraineurEquipefx;
    @FXML
    private TextField CategorieEquipefx;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    
private void AjouterEquipeBack(ActionEvent event) {
    String nom = NomEquipefx.getText();
    String joueurs = JoueursEquipefx.getText();
    int classement = 0;
    String entraineur = EntraineurEquipefx.getText();
    String categorie = CategorieEquipefx.getText();

    int minLength = 6;

    if (nom.isEmpty()) {
        showAlert("Please enter a value for Nom.");
        return;
    }

    if (!Character.isUpperCase(nom.charAt(0))) {
        showAlert("Please enter a valid value for Nom, starting with an uppercase letter.");
        return;
    }

    if (!nom.matches("[a-zA-Z ]+")) {
        showAlert("Please enter a valid value for Nom, containing only letters and spaces.");
        return;
    }

    if (joueurs.isEmpty()) {
        showAlert("Please enter a value for Joueurs.");
        return;
    }

    if (entraineur.isEmpty()) {
        showAlert("Please enter a value for Entraineur.");
        return;
    }

    if (categorie.isEmpty()) {
        showAlert("Please enter a value for Categorie.");
        return;
    }

    if (classement < 0) {
        showAlert("Classement cannot be negative.");
        return;
    }

    if (categorie.length() < minLength) {
        showAlert("Please enter a valid value for Categorie, containing at least " + minLength + " characters.");
        return;
    }

    try {
        classement = Integer.parseInt(ClassementEquipefx.getText());
    } catch (NumberFormatException e) {
        showAlert("Please enter a valid value for Classement, containing only digits.");
        return;
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to add this Equipe?");

    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == ButtonType.OK) {
        Equipe E = new Equipe(nom, joueurs, classement, entraineur, categorie);
        Equipe1CRUD event1 = new Equipe1CRUD();
        event1.ajouterEquipe(E);
        showAlert("Equipe added successfully");
    }

    try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageEquipeBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    }

