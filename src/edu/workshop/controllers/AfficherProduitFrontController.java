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
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private FlowPane catpane;
    @FXML
    private ImageView code_qr;

    
    
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

        // TODO
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
    }



    

