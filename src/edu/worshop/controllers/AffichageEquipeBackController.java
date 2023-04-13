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
 * @author Ace River
 */
public class AffichageEquipeBackController implements Initializable {
        @FXML
    private ListView<Equipe> affichageEquipeBackfx;
    //static Equipe E;
static int id,classement;
static String nom,joueurs,entraineur,categorie;
static Equipe E = new Equipe();

    /**
     * Initializes the controller class.
     */
  @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListView<Equipe> list1 = affichageEquipeBackfx;
EquipeCRUD inter = new Equipe1CRUD();
List<Equipe> list2 = inter.afficherEquipe();
for (int i = 0; i < list2.size(); i++) {
    Equipe E = list2.get(i);
    list1.getItems().add(E); // add Equipe to ListView
}


        } 
    


   @FXML
    private void SupprimerEquipeBack(ActionEvent event) {
    ListView<Equipe> list = (ListView<Equipe>) affichageEquipeBackfx;
    EquipeCRUD inter = new Equipe1CRUD();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Equipe E = list.getSelectionModel().getSelectedItem();
        inter.supprimerEquipe(E.getId());
        list.getItems().remove(selectedIndex);
    } else {
        showAlert("Veuillez sélectionner une Equipe à supprimer.");
    }
    }  
    
 @FXML
    private void AjoutEquipeBack(ActionEvent event) {
    
    try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutEquipeBack.fxml"));
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
    private void ModifierEquipeBack(ActionEvent event) {
        
        ListView<Equipe> list = affichageEquipeBackfx;
        EquipeCRUD inter = new Equipe1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Equipe e = list.getSelectionModel().getSelectedItem();
 
        int id = e.getId();
        String nom = e.getNom();
        String joueurs = e.getJoueurs();
        int classement = e.getClassement();
        String entraineur = e.getEntraineur();
        String categorie = e.getCategorie();
     
        E=e;
        
        
        
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/ModifierEquipeBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);

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
    private void Matchesref(ActionEvent event) {
                try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageMatchesBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutMatchesBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
    }

    @FXML
    private void Equipesref(ActionEvent event) {
              try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageEquipeBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutMatchesBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
    }
 
    }
