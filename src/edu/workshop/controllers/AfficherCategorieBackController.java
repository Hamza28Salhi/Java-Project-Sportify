/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;
import edu.workshop.services.Categorie1CRUD;
import edu.worshop.interfaces.CategorieCRUD;
import edu.worshop.model.Categorie;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AfficherCategorieBackController implements Initializable {

    @FXML
    private ListView<Categorie> AffichageCategorieBackfx;
static int id;
static String nom_categorie;
static Categorie C = new Categorie();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListView<Categorie> list1 = AffichageCategorieBackfx;
CategorieCRUD inter = new Categorie1CRUD();
List<Categorie> list2 = inter.afficherCategorie();
for (int i = 0; i < list2.size(); i++) {
    Categorie C = list2.get(i);
    list1.getItems().add(C); // add Evenement to ListView
    }    
    }

    @FXML
    private void SupprimerCategorieBack(ActionEvent event) {
        ListView<Categorie> list = (ListView<Categorie>) AffichageCategorieBackfx;
    CategorieCRUD inter = new Categorie1CRUD();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Categorie C = list.getSelectionModel().getSelectedItem();
        inter.supprimerCategorie(C.getId());
        list.getItems().remove(selectedIndex);
    } else {
        showAlert("Veuillez sélectionner une categorie à supprimer.");
    }
    }

    @FXML
    private void ModifierCategorieBack(ActionEvent event) {
        
    ListView<Categorie> list = AffichageCategorieBackfx;
        CategorieCRUD inter = new Categorie1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        
if (selectedIndex >= 0) {
        Categorie c = list.getSelectionModel().getSelectedItem();
 
        int id = c.getId();
        
        String nom_categorie = c.getNom_categorie();
        C=c;
       
      
        
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/ModifierCategorieBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherCategorieBackController.class.getName()).log(Level.SEVERE, null, ex);

        }
          } else {
        showAlert("Veuillez sélectionner une categorie à modifier.");
    }
    

    }    

    @FXML
    private void AjouterCategorieBack(ActionEvent event) {
        try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutCategorieBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutCategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
 
}
    private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void AjouteProduitBack(ActionEvent event) {
        ListView<Categorie> list = AffichageCategorieBackfx;
        CategorieCRUD inter = new Categorie1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
         Categorie c = list.getSelectionModel().getSelectedItem();
        C=c;
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutProduitBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherCategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
            

        }
    }

    @FXML
    private void ListeProduitBack(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AfficherProduitBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherCategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
            

        }
    }
    }
