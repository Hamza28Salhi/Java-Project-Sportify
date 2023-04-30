/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Post1CRUD;
import edu.worshop.interfaces.PostCRUD;
import edu.worshop.model.Post;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class Post_ListController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ComboBox<String> comb;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Post> affichagePostBackfx;
    @FXML
    private TableColumn<Post,String> idpostfx;
    @FXML
    private TableColumn<Post,String> titrepostfx;
    @FXML
    private TableColumn<Post,String> auteurpostfx;
    @FXML
    private TableColumn<Post,String> likespostfx;
    @FXML
    private TableColumn<Post,String> dislikespostfx;
    @FXML
    private TableColumn<Post,String> imagepostfx;
    
    private ObservableList<Post> postList= FXCollections.observableArrayList();
    private ObservableList<Post> postList2= FXCollections.observableArrayList();
static int id;
static String titre_Post,contenu_Post,image_Post,auteur_Post;
static Post P = new Post();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ObservableList<String> listt = FXCollections.observableArrayList("Ordre alphabetique","date de creation");
        comb.setItems(listt);
        
        
        // TODO
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        label.setFont(font);
        idpostfx.setCellValueFactory(new PropertyValueFactory<>("id"));
        titrepostfx.setCellValueFactory(new PropertyValueFactory<>("titrePost"));
        auteurpostfx.setCellValueFactory(new PropertyValueFactory<>("auteurPost"));
        likespostfx.setCellValueFactory(new PropertyValueFactory<>("likes"));
        dislikespostfx.setCellValueFactory(new PropertyValueFactory<>("dislike"));
             
        TableView<Post> list1 =  affichagePostBackfx; //ListView<Post> list1 = affichagePostBackfx;
PostCRUD inter = new Post1CRUD();
List<Post> list2 = inter.afficherPost();
postList = FXCollections.observableArrayList(list2);
for (int i = 0; i < list2.size(); i++) {
    Post P = list2.get(i);
    list1.getItems().add(P); // add Post to ListView
}
// Ajouter une fonction de recherche
        FilteredList<Post> filteredList = new FilteredList<>(postList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(Post -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (Post.getAuteurPost().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par nom complet
                } else if (Post.getContenuPost().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par adresse e-mail
                } else if (String.valueOf(Post.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par ID
                } else if (Post.getTitrePost().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par date de naissance
                }

                return false; // Aucune correspondance trouvée
            });
        });

        SortedList<Post> sortedList = new SortedList<>(filteredList);
        list1.setItems(sortedList);
    }

    
     private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
    
    @FXML
    private void AjoutPostBack(ActionEvent event) {
        try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Post_Add.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(Post_AddController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
    }

    @FXML
private void SupprimerPostBack(ActionEvent event) {
    Post selectedPost = affichagePostBackfx.getSelectionModel().getSelectedItem();
    if (selectedPost == null) {
        showAlert("Veuillez sélectionner un post à supprimer.");
    } else {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer le post " + selectedPost.getTitrePost() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            PostCRUD inter = new Post1CRUD();
            inter.supprimerPost(selectedPost.getId());
            postList.remove(selectedPost);
            showAlert("Le post " + selectedPost.getTitrePost() + " a été supprimé avec succès.");
        }
    }
}


 @FXML
private void ModifierPostBack(ActionEvent event) {
    // Récupérer le Post sélectionné dans la TableView
    Post postSelected = affichagePostBackfx.getSelectionModel().getSelectedItem();
    
    if (postSelected == null) {
        showAlert("Veuillez sélectionner un Post à modifier !");
    } else {
        try {
            // Ouvrir la page de modification du Post
            P=postSelected;
            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Post_Update.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
            // Envoyer les données du Post à modifier à la nouvelle page
            
            
            // Afficher la page de modification
            
        } catch (IOException ex) {
            Logger.getLogger(Post_ListController.class.getName()).log(Level.SEVERE, null, ex);
            showAlert("Erreur lors de l'ouverture de la page de modification !");
        }
    }
}


    @FXML
    private void AjoutCommentaireBack(ActionEvent event) {
    }

    @FXML
    private void AffichageCommentaireBack(ActionEvent event) {
            Post postSelected = affichagePostBackfx.getSelectionModel().getSelectedItem();
         Post p = postSelected;
                     PostCRUD inter = new Post1CRUD();
         int nbrRating = inter.recupererNbrRating(p);

        P= new Post(p.getId(),p.getTitrePost(),p.getContenuPost(),p.getImagePost(),p.getAuteurPost(),p.getRating(),nbrRating);
        
         System.out.println("nbRating?"+nbrRating);

        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Commentaire_List.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Post_ListController.class.getName()).log(Level.SEVERE, null, ex);
            

        }
    }

    @FXML
private void TriPostBack(ActionEvent event) {
    if (comb != null) {
        String s = comb.getSelectionModel().getSelectedItem();
        if (s != null && s.equals("Ordre alphabetique")) {
            // Récupération de la liste des posts
            PostCRUD inter = new Post1CRUD();
            List<Post> posts = inter.afficherPost();

            // Tri des posts selon le titre en ordre alphabétique croissant
            Collections.sort(posts, new Comparator<Post>() {
                @Override
                public int compare(Post p1, Post p2) {
                    return p1.getTitrePost().compareToIgnoreCase(p2.getTitrePost());
                }
            });

            // Affichage des posts triés
            postList = FXCollections.observableArrayList(posts);
            TableView<Post> list1 = affichagePostBackfx;
            list1.setItems(postList);
            
            
            
            // Ajouter une fonction de recherche
        FilteredList<Post> filteredList = new FilteredList<>(postList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(Post -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (Post.getAuteurPost().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par nom complet
                } else if (Post.getContenuPost().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par adresse e-mail
                } else if (String.valueOf(Post.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par ID
                } else if (Post.getTitrePost().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par date de naissance
                } 

                return false; // Aucune correspondance trouvée
            });
        });

        SortedList<Post> sortedList = new SortedList<>(filteredList);
        list1.setItems(sortedList);
            
     
        }
        
        if(s!=null & s.equals("date de creation")){
            TableView<Post> list1 = affichagePostBackfx;
    PostCRUD inter = new Post1CRUD();
    List<Post> list2 = inter.afficherPost();
    postList = FXCollections.observableArrayList(list2);
    list1.setItems(postList);
        }
    }
    
}

    @FXML
    private void StatPostBack(ActionEvent event) {
           try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Graphdata.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(Post_AddController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
    }

    
}
