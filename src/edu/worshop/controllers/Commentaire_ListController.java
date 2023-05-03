/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Commentaire1CRUD;
import edu.workshop.services.Post1CRUD;
import static edu.worshop.controllers.Post_ListController.P;
import edu.worshop.interfaces.CommentaireCRUD;
import edu.worshop.interfaces.PostCRUD;
import edu.worshop.model.Commentaire;
import edu.worshop.model.Post;
import java.io.File;
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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author sammo
 */
public class Commentaire_ListController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ComboBox<?> comb;
    @FXML
    private TextField searchField;
    @FXML
    private ListView<?> affichageCommentaireBackfx;
    @FXML
    private ImageView PostImage;

    static int id;
static String titre_Post,contenu_Post,image_Post,auteur_Post;
static Commentaire C = new Commentaire();  
    @FXML
    private TextArea espacepost;
    @FXML
    private Rating PostRating;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                // TODO

                ListView<Commentaire> list1 = (ListView<Commentaire>) affichageCommentaireBackfx; //ListView<Post> list1 = affichagePostBackfx;
CommentaireCRUD inter = new Commentaire1CRUD();
List<Commentaire> list2 = inter.afficherCommentaireParPostId(Post_ListController.P.getId());

File file = new File("upload/" + P.getImagePost());
            Image image = new Image(file.toURI().toString());
            PostImage.setImage(image);
for (int i = 0; i < list2.size(); i++) {
    Commentaire C = list2.get(i);
    list1.getItems().add(C); // add Post to ListView
}

espacepost.setText(Post_ListController.P.getContenuPost());
 //System.out.println("est ce que cezt zero?"+Post_ListController.P.getNbr_rating());
 //System.out.println("et le titre alors??"+Post_ListController.P.getTitrePost());
//XYChart.Series chartdata= new XYChart.Series();
    }   

    @FXML
    private void TriPostBack(ActionEvent event) {
    }

    private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    private void SupprimerCommentaireBack(ActionEvent event) {
            ListView<Commentaire> list = (ListView<Commentaire>) affichageCommentaireBackfx;
    CommentaireCRUD inter = new Commentaire1CRUD();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {      
        Commentaire C = list.getSelectionModel().getSelectedItem();
        inter.supprimerCommentaire(C.getId());
        list.getItems().remove(selectedIndex);
    } else {
        showAlert("Veuillez sélectionner un commentaire à supprimer.");
    }
    }

    @FXML
    private void AjoutCommentaireBack(ActionEvent event) {
                        
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Commentaire_Add.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Commentaire_ListController.class.getName()).log(Level.SEVERE, null, ex);
            

        }
    
    }

    @FXML
    private void ModifierCommentaireBack(ActionEvent event) {
         ListView<Commentaire> list = (ListView<Commentaire>) affichageCommentaireBackfx; //ListView<Post> list = affichagePostBackfx;
        CommentaireCRUD inter = new Commentaire1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        
  if (selectedIndex >=0){
            
        
        Commentaire c = list.getSelectionModel().getSelectedItem();

        /*int id = c.getId();
        int post_id = c.getPost_id();
        String contenu_Commentaire = c.getContenuCommentaire();
        String auteur_Commentaire = c.getAuteurCommentaire();*/
        C=c;
      
        
        
        
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Commentaire_Update.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Commentaire_ListController.class.getName()).log(Level.SEVERE, null, ex);
        //AffichagePostBackController
        }
        }else{showAlert("Veuillez sélectionner un commentaire à modifier.");
  }
    }

    @FXML
    private void RetourPostBack(ActionEvent event) throws IOException {
    Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Post_List.fxml"));
    Scene scene = new Scene(page1);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}

    @FXML
    private void rating(ActionEvent event) {
        PostCRUD inter = new Post1CRUD();
                
        System.out.println("rating given: "+ PostRating.getRating());
        if(Post_ListController.P.getNbr_rating()==0)
        {
                                         System.out.println("le nombre de fois qu'on a noté ce post est:"+Post_ListController.P.getNbr_rating());

            //P.setRating(PostRating.getRating());
            //P.setNbr_rating(1);
            Post even = new Post(Post_ListController.P.getId(),Post_ListController.P.getTitrePost(),Post_ListController.P.getContenuPost(),Post_ListController.P.getImagePost(),Post_ListController.P.getAuteurPost(),PostRating.getRating(),1 );
                inter.modifierPost(even);
                //inter.afficherrPost();
                System.out.println(even);
                System.out.println("------------------------"+even.getNbr_rating());
                                System.out.println(even.getRating());
                                System.out.println(even.getNbr_rating());
                                

        }
        else{
            System.out.println("le nombre de fois qu'on a noté ce post est:"+Post_ListController.P.getNbr_rating());
            Post P = new Post(Post_ListController.P.getId(),Post_ListController.P.getTitrePost(),Post_ListController.P.getContenuPost(),Post_ListController.P.getImagePost(),Post_ListController.P.getAuteurPost(),PostRating.getRating(),Post_ListController.P.getNbr_rating() );
             P.setNbr_rating(P.getNbr_rating()+1);
                             System.out.println("nouv ratnbr"+P.getNbr_rating());

             int coeff=P.getNbr_rating()+1;
             double moyenne_ancienne= (P.getRating()/coeff);
             double moyenne_one_rate = (PostRating.getRating()/coeff);
             double moyenne_nouvelle=moyenne_ancienne+moyenne_one_rate;
             P.setRating(moyenne_nouvelle);
              //Post even = new Post(Post_ListController.P.getTitrePost(),Post_ListController.P.getContenuPost(),Post_ListController.P.getImagePost(),Post_ListController.P.getAuteurPost(),P.getRating(),P.getNbr_rating() );
                inter.modifierPost(P);
                System.out.println(P);
                                System.out.println(P.getRating());
                                System.out.println(P.getNbr_rating());
                                
        }
    }
    
}
