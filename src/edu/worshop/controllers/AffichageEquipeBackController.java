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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
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
static String nom,joueurs,entraineur,categorie,picture;
static Equipe E = new Equipe();
    @FXML
    private PieChart pieChart;
        private ObservableList<Equipe> equipeList;
    @FXML
    private TextField searchField;


    /**
     * Initializes the controller class.
     */
  @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListView<Equipe> list1 = affichageEquipeBackfx;
EquipeCRUD inter = new Equipe1CRUD();
List<Equipe> list2 = inter.afficherEquipe();
equipeList = FXCollections.observableArrayList(list2);

    
    
for (int i = 0; i < list2.size(); i++) {
    Equipe E = list2.get(i);
    list1.getItems().add(E); // add Equipe to ListView
}
  list1.setCellFactory(param -> new ListCell<Equipe>() {
            @Override
            protected void updateItem(Equipe item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getId() + "   |   " + item.getNom() + "   |   " + item.getJoueurs() + "   |   " + item.getClassement() + "   |   " + item.getEntraineur() + "   |   " + " (" + item.getCategorie() + ")");
                }
            }
        });

        // Ajouter une fonction de recherche
        FilteredList<Equipe> filteredList = new FilteredList<>(equipeList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(e -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if ( e.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par nom complet
                } else if (e.getJoueurs().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par adresse e-mail
                } else if (String.valueOf(e.getClassement()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par ID
                } else if (e.getEntraineur().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par date de naissance
                } else if (e.getCategorie().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par adresse
                }

                return false; // Aucune correspondance trouvée
            });
        });

        SortedList<Equipe> sortedList = new SortedList<>(filteredList);
        list1.setItems(sortedList);

        
 

        } 
    
    
    
    
    
    
     String path = "C:\\xampp\\htdocs\\music\\Hazim.mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);


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

    @FXML
    private void musicButton(ActionEvent event) {
             mediaPlayer.play();
       // Image img = new Image("C:\\xampp\\htdocs\\music\\ala.jpg");
             
//         Notifications notificationBuilder = Notifications.create()
//                .title("Musique")
//                .text("      Musique Jouée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
//                .position(Pos.BOTTOM_RIGHT);
     
    }

    @FXML
    private void pauseMusicButton(ActionEvent event) {
           mediaPlayer.pause();
        //Image img = new Image("fllogo.png");
      
    }

    
    
    
    
 
    }
