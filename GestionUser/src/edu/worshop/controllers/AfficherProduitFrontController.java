/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import edu.workshop.services.Categorie1CRUD;
import edu.workshop.services.Produit1CRUD;
import edu.worshop.interfaces.CategorieCRUD;
import edu.worshop.interfaces.ProduitCRUD;
import edu.worshop.model.Categorie;
import edu.worshop.model.Produit;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;
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
    private Text label;
    @FXML
    private Button profile;
    @FXML
    private Button blog;
    @FXML
    private Button qr;
    @FXML
    private Button blog1;

   
   
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
    list1.getItems().add(P);
}
list1.setCellFactory(param -> new ListCell<Produit>() {
    @Override
    protected void updateItem(Produit item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText( "  " + item.getNom_produit() + "     " + item.getPrix_produit() + "     " + item.getMarque_produit() + "     " + " (" + item.getQuantite() + ")");
            ImageView imageView = new ImageView();
           
            imageView.setFitHeight(70);
            imageView.setFitWidth(70);
            File file = new File("upload/" + item.getImage());
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);
                setGraphic(imageView);
            }
        }
    }
});


Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
profile.setFont(buttonfont);
blog.setFont(buttonfont);
qr.setFont(buttonfont);
blog1.setFont(buttonfont);






         
         
}

   



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

    private void redirecttoproduct(ActionEvent event) {
        try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/Post_List.fxml"));
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
    private void redirecttoblog(ActionEvent event) {
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/PostFront_List.fxml"));
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