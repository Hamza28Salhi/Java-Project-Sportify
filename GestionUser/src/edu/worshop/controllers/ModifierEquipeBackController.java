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
import edu.worshop.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Ace River
 */
public class ModifierEquipeBackController implements Initializable {

    @FXML
    private TextField NomEquipeMfx;
    @FXML
    private TextField JoueursEquipeMfx;
    @FXML
    private TextField ClassementEquipeMfx;
    @FXML
    private TextField EntraineurEquipeMfx;
    @FXML
    private TextField CategorieEquipeMfx;
    static int id;
    static String nom;
    static String joueurs;
    static String classement;
    static String entraineur;
    static String categorie;
    static String picture;
    @FXML
    private ImageView Imageview;
    @FXML
    private Text label;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //DateMfx.setDate(new SimpleDateFormat("dd/MM/yyyy").format(DateMfx));4
        //DateMfx= ava.sql.Date.setText(AtomDate.valueOf(date).getValue());
        //DateMfx = (Date) java.sql.Date.valueOf(AffichageEquipeBackController.E.getDate());
        NomEquipeMfx.setText(String.valueOf(AffichageEquipeBackController.E.getNom()));
        JoueursEquipeMfx.setText(String.valueOf(AffichageEquipeBackController.E.getJoueurs()));
        ClassementEquipeMfx.setText(String.valueOf(AffichageEquipeBackController.E.getClassement()));
        EntraineurEquipeMfx.setText(String.valueOf(AffichageEquipeBackController.E.getEntraineur()));
        CategorieEquipeMfx.setText(String.valueOf(AffichageEquipeBackController.E.getCategorie()));
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        label.setFont(font);

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void ModifierEquipeBack(ActionEvent event) {

        // Retrieve the id of the Equipe to update
        int id = AffichageEquipeBackController.E.getId();

        // Retrieve the new values from the text fields
        String nom = NomEquipeMfx.getText().trim();
        String joueurs = JoueursEquipeMfx.getText().trim();
        String entraineur = EntraineurEquipeMfx.getText().trim();
        String categorie = CategorieEquipeMfx.getText().trim();
        int classement = 0;
        String picture = MyConnection.getImage_Name();

        // Check that Classement is a positive integer
        try {
            classement = Integer.parseInt(ClassementEquipeMfx.getText().trim());
            if (classement < 0) {
                showAlert("Classement doit être positif");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Classement doit être un entier");
            return;
        }

        // Check that Nom, Joueurs, Entraineur, and Categorie are not empty
        if (nom.isEmpty()) {
            showAlert("Nom est vide");
            return;
        }
        if (joueurs.isEmpty()) {
            showAlert("Joueurs est vide");
            return;
        }
        if (entraineur.isEmpty()) {
            showAlert("Entraineur est vide");
            return;
        }
        if (categorie.isEmpty()) {
            showAlert("Categorie est vide");
            return;
        }

        // Check that Nom contains only letters and starts with an uppercase letter
        if (!nom.matches("[A-Z][a-zA-Z]*")) {
            showAlert("Nom doit commencer par une lettre majuscule et ne doit contenir que des lettres");
            return;
        }

        if (!entraineur.matches("[A-Z][a-zA-Z]*")) {
            showAlert("entraineur doit commencer par une lettre majuscule et ne doit contenir que des lettres");
            return;
        }
        if (!categorie.matches("[A-Z][a-zA-Z]*")) {
            showAlert("categorie doit commencer par une lettre majuscule et ne doit contenir que des lettres");
            return;
        }

        // Create a new Equipe object with the updated values
        Equipe updatedEquipe = new Equipe(id, nom, joueurs, classement, entraineur, categorie, picture);

        // Update the Equipe in the database using the Equipe1CRUD class
        EquipeCRUD equipeCRUD = new Equipe1CRUD();
        equipeCRUD.modifierEquipe(updatedEquipe);

        // Display a success message
        showAlert("Equipe updated successfully");

        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageEquipeBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);
            //showAlert("Error loading");
        }
    }

    @FXML
    private void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image file");
        fileChooser.getExtensionFilters().addAll();
        Window window = ((Node) event.getTarget()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(window);
        if (selectedFile != null) {
            try {
                // Create a directory called "upload" if it doesn't exist
                File uploadDir = new File("uploadd");
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                // Copy the selected file to the "upload" directory
                String fileName = selectedFile.getName();
                File destFile = new File("uploadd/" + fileName);
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                // Set the ImageView's image to the selected image
                Image image = new Image(destFile.toURI().toString());
                Imageview.setImage(image);
                //Save the image name in img_Saver
                MyConnection.setImage_Name(fileName);
            } catch (IOException ex) {
                Logger.getLogger(AjoutEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
