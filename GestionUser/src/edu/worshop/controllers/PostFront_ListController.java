/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Post1CRUD;
import static edu.worshop.controllers.Post_ListController.P;
import edu.worshop.interfaces.PostCRUD;
import edu.worshop.model.Post;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sammo
 */
public class PostFront_ListController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ComboBox<String> comb;
    @FXML
    private TextField searchField;
    @FXML
    private ListView<?> User_Listfx;
    
    private ObservableList<Post> postList= FXCollections.observableArrayList();
    static int f_b=0;
    @FXML
    private Button qr;
    private Button qr1;
    @FXML
    private Button qr2;
    @FXML
    private Button qr21;
    @FXML
    private Button qr211;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listt = FXCollections.observableArrayList("Ordre alphabetique","date de creation");
        comb.setItems(listt);
        
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        qr.setFont(buttonfont);
        
        qr2.setFont(buttonfont);
        qr21.setFont(buttonfont);
        qr211.setFont(buttonfont);
        

        ListView<Post> list1 = (ListView<Post>) User_Listfx;
        PostCRUD inter = new Post1CRUD();
        List<Post> list2 = inter.afficherPost();
        postList = FXCollections.observableArrayList(list2);

        for (int i = 0; i < list2.size(); i++) {
    Post P = list2.get(i);
    list1.getItems().add(P); // add Equipe to ListView
}
        
        list1.setCellFactory(param -> new ListCell<Post>() {
    @Override
    protected void updateItem(Post item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            Text title = new Text(item.getTitrePost());
            title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
 Text content = new Text(item.getContenuPost());
            if (item.getContenuPost().length() > 50) {
                content.setText(item.getContenuPost().substring(0, 50) + "...");
            }            
            VBox container = new VBox(title, content);
            container.setSpacing(5);

            ImageView imageView = new ImageView();
            imageView.setFitHeight(90);
            imageView.setFitWidth(120);
            File file = new File("upload/" + item.getImagePost());
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);
            }
            setGraphic(new HBox(imageView, container));
        }
    }
});

        
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
            ListView<Post> list1 = (ListView<Post>) User_Listfx;
            //TableView<Post> list1 = affichagePostBackfx;
            list1.setItems(postList);
            
            
            
            rech();

        SortedList<Post> sortedList = new SortedList<>(rech());
        list1.setItems(sortedList);
            
     
        }
        
        if(s!=null & s.equals("date de creation")){
            ListView<Post> list1 = (ListView<Post>) User_Listfx;
    PostCRUD inter = new Post1CRUD();
    List<Post> list2 = inter.afficherPost();
    postList = FXCollections.observableArrayList(list2);
    list1.setItems(postList);
     rech();

        SortedList<Post> sortedList = new SortedList<>(rech());
        list1.setItems(sortedList);

        }
    }
    
        
        
        rech();
    }

    @FXML
    private void CommentaireFShow(ActionEvent event) {
                    Post postSelected = (Post) User_Listfx.getSelectionModel().getSelectedItem();
         Post p = postSelected;
                     PostCRUD inter = new Post1CRUD();
         int nbrRating = inter.recupererNbrRating(p);
         int Rrating = inter.recupererRating(p);

        P= new Post(p.getId(),p.getTitrePost(),p.getContenuPost(),p.getImagePost(),p.getAuteurPost(),Rrating,nbrRating);
        
         System.out.println("nbRating?"+nbrRating);
        f_b=1;
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/CommentaireFront_List.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Post_ListController.class.getName()).log(Level.SEVERE, null, ex);
            

        }
    }

    
    private FilteredList rech(){
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
        return filteredList;
    }
    
    @FXML
    private void redirecttoprofile(ActionEvent event) {
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/Front_Profile.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                    
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

    @FXML
    private void redirecttoshop(ActionEvent event) {
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/AfficherProduitFront.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                    
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

    @FXML
    private void redirecttomatch(ActionEvent event) {
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/AffichageMatchesFront.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                    
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
}
