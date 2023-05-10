/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Evenement1CRUD;
import edu.worshop.interfaces.EvenementCRUD;
import edu.worshop.model.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.util.Collections.list;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HOUYEM
 */
public class ModifierEvenementBackController implements Initializable {

    @FXML
    private DatePicker DateMfx;
    @FXML
    private TextField TypeMfx;
    @FXML
    private TextField LieuMfx;
    @FXML
    private TextArea DescriptionMfx;
    @FXML
    private TextField Even_picMfx;
    @FXML
    private TextField TitreMfx;
    static int id;
    static Date date;
    static String type;
    static String lieu;
    static String description;
    static String even_pic;
    static String titre;
    @FXML
    private Text label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //DateMfx.setDate(new SimpleDateFormat("dd/MM/yyyy").format(DateMfx));4
        //DateMfx= ava.sql.Date.setText(AtomDate.valueOf(date).getValue());
        //DateMfx = (Date) java.sql.Date.valueOf(AffichageEvenementBackController.E.getDate());
        TypeMfx.setText(String.valueOf(AffichageEvenementBackController.E.getType()));
        LieuMfx.setText(String.valueOf(AffichageEvenementBackController.E.getLieu()));
        DescriptionMfx.setText(String.valueOf(AffichageEvenementBackController.E.getDescription()));
        Even_picMfx.setText(String.valueOf(AffichageEvenementBackController.E.getEven_pic()));
        TitreMfx.setText(String.valueOf(AffichageEvenementBackController.E.getTitre()));

        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        label.setFont(font);

    }

    @FXML
    private void ModifierEvenementBack(ActionEvent event) {

        EvenementCRUD inter = new Evenement1CRUD();
        int id;
        Date date = java.sql.Date.valueOf(DateMfx.getValue());
        String type = TypeMfx.getText();
        String lieu = LieuMfx.getText();
        String description = DescriptionMfx.getText();
        String even_pic = Even_picMfx.getText();
        String titre = TitreMfx.getText();

        int minLength = 6;
        int minLength1 = 4;
        LocalDate currentDate = LocalDate.now(); // Gets the current date
        String dateStringlocal = currentDate.toString();
        String datee = date.toString();
        int comparaison = datee.compareTo(dateStringlocal);
        if (comparaison < 0) {
            showAlert("il faut que la date est supérieure à la date système");
        } else if (type.isEmpty()) {
            showAlert("le type est vide ");
        } else if (!type.matches(".*([a-zA-Z])+")) {
            showAlert("le type doit être alphabétique ");
        } else if (type.matches(".*([a-zA-Z])\\1{2,}.*")) {
            showAlert("La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement.");
        } else if (type.length() < minLength1) {
            showAlert("Le type doit contenir au moins " + minLength1 + " caractères");

        } else if (lieu.isEmpty()) {
            showAlert("Le lieu est vide ");
        } else if (!lieu.matches("[a-zA-Z]+")) {
            showAlert("le lieu doit être alphabétique ");
        } else if (lieu.matches(".*([a-zA-Z])\\1{2,}.*")) {
            showAlert("La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement.");
        } else if (lieu.length() < minLength1) {
            showAlert("Le lieu doit contenir au moins " + minLength1 + " caractères");

        } else if (description.isEmpty()) {
            showAlert("La description est vide ");
        } else if (!description.matches("[a-zA-Z]+")) {
            showAlert("la descripiton doit être alphabétique ");
        } else if (description.matches(".*([a-zA-Z])\\1{2,}.*")) {
            showAlert("La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement.");
        } else if (description.length() < minLength) {
            showAlert("La description doit contenir au moins " + minLength + " caractères");
        } else if (even_pic.isEmpty()) {
            showAlert("L'image est vide ");

        } else if (titre.isEmpty()) {
            showAlert("Le titre est vide ");
        } else if (titre.length() < minLength1) {
            showAlert("Le titre doit contenir au moins " + minLength1 + " caractères");
        } else if (!titre.matches("[a-zA-Z]+")) {
            showAlert("le titre doit être alphabétique ");
        } else if (titre.matches(".*([a-zA-Z])\\1{2,}.*")) {
            showAlert("La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement.");
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("are you sure to add this event ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Evenement even = new Evenement(AffichageEvenementBackController.E.getId(), date, type, lieu, description, even_pic, titre);
                inter.modifierEvenement(even);

                showAlert("l'évenement a été modifié avec succès ");
            }

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageEvenementBack.fxml"));
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

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
