/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import static edu.workshop.controllers.AfficherCategorieBackController.C;
import edu.workshop.services.Categorie1CRUD;
import edu.workshop.services.Produit1CRUD;
import edu.worshop.interfaces.CategorieCRUD;
import edu.worshop.interfaces.ProduitCRUD;
import edu.worshop.model.Categorie;
import edu.worshop.model.Produit;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AfficherProduitFrontController implements Initializable {

    @FXML
   private ListView<Produit> AfficherProduitFrontfx;
      static int categorie_id;
static String nom_produit;
static int prix_produit;
static String marque_produit, image;
static int quantite;
static Produit P = new Produit();
      //  private ObservableList<Produit> userList;

    @FXML
    private FlowPane catpane;
   
    @FXML
    private ImageView code_qr;
    @FXML
    private Pagination pagination;
    @FXML
    private TextField searchfield;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ListView<Produit> list1 = AfficherProduitFrontfx;
ProduitCRUD inter = new Produit1CRUD();
List<Produit> list2 = inter.afficherProduit();
for (int i = 0; i < list2.size(); i++) {
    Produit P = list2.get(i);
    list1.getItems().add(P); // add Evenement to ListView
          // refrechpane();
          
          
}
// // Ajouter une fonction de recherche
//        FilteredList<Produit> filteredList = new FilteredList<>(userList, p -> true);
//        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredList.setPredicate(Produit -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (Produit.getNom_produit().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Recherche par nom complet
//                } else if (Produit.getMarque_produit().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Recherche par adresse e-mail
//                } 
//
//                return false; // Aucune correspondance trouvée
//            });
//        });
//
//        SortedList<Produit> sortedList = new SortedList<>(filteredList);
//        list1.setItems(sortedList);
//        // TODO



  }  
   

    /**
     * Initializes the controller class.
     */
   

   /* public void refrechpane() {
        catpane.getChildren().clear();
        Produit1CRUD sp = new Produit1CRUD();
        ObservableList<Produit> listuser = FXCollections.observableArrayList();
        listuser = sp.afficherproduit();
        
        for (Produit us : listuser) {

       VBox card = new VBox();
            card.setPrefSize(250, 250);
            card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");

            ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream("C:/imagepi/" + us.getImage())));
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                imageView.setClip(new Circle(75, 75, 75));
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AfficherProduitFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Label nameLabel = new Label(us.getNom_produit());
            Label prixLabel = new Label(String.valueOf(us.getPrix_produit()));
            Label marqueLabel = new Label(us.getMarque_produit());
            Label quantiteLabel = new Label(String.valueOf(us.getQuantite()));

            nameLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            nameLabel.setAlignment(Pos.CENTER);
            nameLabel.setStyle("-fx-text-fill: gray;");

            prixLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            prixLabel.setAlignment(Pos.CENTER);
            prixLabel.setStyle("-fx-text-fill: gray;");

            marqueLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            marqueLabel.setAlignment(Pos.CENTER);
            marqueLabel.setStyle("-fx-text-fill: gray;");
            
             quantiteLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            quantiteLabel.setAlignment(Pos.CENTER);
            quantiteLabel.setStyle("-fx-text-fill: gray;");

            card.getChildren().add(nameLabel);
            card.getChildren().add(prixLabel);
            card.getChildren().add(marqueLabel);
            card.getChildren().add(quantiteLabel);

            Button btn1 = new Button("Supprimer");
            btn1.setAlignment(Pos.TOP_RIGHT);
            btn1.setStyle("-fx-background-color: #1372f4; -fx-background-radius: 25px; -fx-text-fill: white;");
            btn1.setOnAction(e -> {
                Produit1CRUD ps = new Produit1CRUD();
                
               refrechpane();
            });
            card.getChildren().add(btn1);
            catpane.getChildren().add(card);
            catpane.setMargin(card, new Insets(5, 5, 5, 5));
        }
    }*/

    @FXML
    private void qr_code(ActionEvent event) {
        Produit P = AfficherProduitFrontfx.getSelectionModel().getSelectedItem();
      

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "idCategorie : "+P.getCategorie_id()+"\n"+"NomProduit:  "+P.getNom_produit()+"\n"+"PrixProduit : "+P.getPrix_produit()+"\n"+"MarqueProduit : "+P.getMarque_produit()+"\n"+"Quantite : "+P.getQuantite();
        int width = 300;
        int height = 300;
        BufferedImage bufferedImage = null;
         try{
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
            code_qr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            
        } catch (WriterException ex) {
        }
          
    }
    
    
   /* @FXML
    Pagination pagination;
    
    
    public void start(final Stage stage) throws Exception {
    
    pagination = new Pagination(10);
    pagination.setStyle("-fx-border-color:blue");
    pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));
    
    
    AnchorPane anchor = new AnchorPane();
    AnchorPane.setTopAnchor(pagination, 10.0);
    AnchorPane.setBottomAnchor(pagination, 10.0);
    AnchorPane.setLeftAnchor(pagination, 10.0);
    AnchorPane.setRightAnchor(pagination, 10.0);
   
    anchor.getChildren().add(pagination);
    Scene scene = new Scene(anchor);
    
    stage.setScene(scene);
    stage.show();
    
    }
    
    public VBox createPage(int pageIndex)
    { VBox pageBox = new VBox();
    Label pageLabel = new Label("Page : "+ (pageIndex+1));
    pageBox.getChildren().add(pageLabel);
    
    return pageBox;
    }
    */
   // public static void main(String[] args) throws Exception {
      //  Launch(args);
   // }
    
    
    public VBox createPage(int pageIndex) {
    VBox pageBox = new VBox();
    pageBox.setSpacing(2);

    // Calculer les indices des deux produits à afficher
    int index1 = pageIndex * 2;
    int index2 = index1 + 1;
    if (index1 >= AfficherProduitFrontfx.getItems().size()) {
        return pageBox;
    }

    // Créer un VBox pour chaque produit à afficher
    VBox box1 = createProductBox(AfficherProduitFrontfx.getItems().get(index1));
    pageBox.getChildren().add(box1);

    if (index2 < AfficherProduitFrontfx.getItems().size()) {
        VBox box2 = createProductBox(AfficherProduitFrontfx.getItems().get(index2));
        pageBox.getChildren().add(box2);
    }

    return pageBox;
}

private VBox createProductBox(Produit produit) {
    VBox productBox = new VBox();
    productBox.setAlignment(Pos.CENTER);
    productBox.setSpacing(2);

    Label nomLabel = new Label(produit.getNom_produit());
    Label prixLabel = new Label(String.valueOf(produit.getPrix_produit()));
    ImageView imageView = new ImageView(new Image(produit.getImage()));

    productBox.getChildren().addAll(imageView, nomLabel, prixLabel);

    return productBox;
}

    @FXML
    private void trier(ActionEvent event) {
        ObservableList<Produit> listeProduits = AfficherProduitFrontfx.getItems();
    Collections.sort(listeProduits, new Comparator<Produit>() {
        @Override
        public int compare(Produit p1, Produit p2) {
            return p1.getMarque_produit().compareToIgnoreCase(p2.getMarque_produit());
        }
    });
   AfficherProduitFrontfx.setItems(listeProduits);
    }

   

}


    

