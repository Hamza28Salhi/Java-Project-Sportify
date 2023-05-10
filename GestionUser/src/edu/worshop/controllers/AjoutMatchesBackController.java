/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Equipe1CRUD;
import edu.workshop.services.Matches1CRUD;
import static edu.worshop.controllers.ModifierMatchesBackController.nomEquipeid;
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
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

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
    private TextField NomEquipeIdMatchesfx;
    @FXML
    private DatePicker DateMatchesfx;
    @FXML
    private ChoiceBox<Equipe> boxCategorie;
    @FXML
    private Text label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Equipe1CRUD cs = new Equipe1CRUD();
        List<Equipe> testList = cs.afficherEquipe();
        boxCategorie.setItems(FXCollections.observableArrayList(testList));
        boxCategorie.setConverter(new StringConverter<Equipe>() {

            @Override
            public String toString(Equipe equipe) {
                return equipe == null ? "" : equipe.getNom();
            }

            @Override
            public Equipe fromString(String string) {
                // Find the Equipe object that matches the selected string
                for (Equipe equipe : boxCategorie.getItems()) {
                    if (equipe.getNom().equals(string)) {
                        return equipe;
                    }
                }

                // If no match is found, return null
                return null;
            }
        });

        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        label.setFont(font);

    }

    @FXML
    private void AjouterMatchesBack(ActionEvent event) {
        if (NomMatchesfx.getText().isEmpty() || StadeMatchesfx.getText().isEmpty() || ScoreMatchesfx.getText().isEmpty() || DateMatchesfx.getValue() == null) {
            showAlert("Please fill in all fields.");
            return;
        }
        //String Equipe_id = Equipe_idRfx.getValue();
        Date date = java.sql.Date.valueOf(DateMatchesfx.getValue());
        String nom = NomMatchesfx.getText().trim();
        if (nom.isEmpty() || !Character.isUpperCase(nom.charAt(0)) || !nom.matches("[a-zA-Z ]+")) {
            showAlert("Please enter a valid value for Nom, starting with an uppercase letter and without symbols.");
            return;
        }
        nom = Character.toUpperCase(nom.charAt(0)) + nom.substring(1);

        LocalDate currentDate = LocalDate.now(); // Gets the current date
        String dateStringlocal = currentDate.toString();
        String datee = date.toString();
        int comparaison = datee.compareTo(dateStringlocal);
        if (comparaison < 0) {
            showAlert("il faut que la date est supérieure à la date système");
        }

        String stade = StadeMatchesfx.getText();
        String score = ScoreMatchesfx.getText();

        Integer nomEquipeId = boxCategorie.getValue().getId();

        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("are you sure to add this Matches ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                Matches R = new Matches(nom, stade, date, score, nomEquipeId);
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
