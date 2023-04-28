/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotify.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import static spotify.controller.AddAbonnementController.copier;
import spotify.entities.Abonnement;
import spotify.entities.Categorie;
import spotify.services.AbonnementService;
import spotify.services.CategorieService;

/**
 * FXML Controller class
 *

 */
public class ModefierAbonnementController implements Initializable {

    @FXML
    private ImageView imv;

    int c;
    int file;
    File pDir;
    File pfile;
    String lien;
    
    CategorieService categorieService = new CategorieService();
    AbonnementService abonnementService = new AbonnementService();
    
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField prix;
    @FXML
    private ChoiceBox<String> chexbox;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
            pDir = new File("src/spotify/view/img/abonement" + c + ".jpg");
            lien = "abonement" + c + ".jpg";
            List<Categorie> l = categorieService.getAllCategories();
            
            for(Categorie c : l ){
                chexbox.getItems().addAll(c.getDescription());
            }
                
                
                // TODO
                } catch (SQLDataException ex) {
            Logger.getLogger(AddAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Abonnement e = abonnementService.get_Abonnement(AffichierAbonnementController.ide);
        System.out.println("hello"+AffichierAbonnementController.ide);        
        System.out.println("hello"+e.toString());
        lien = e.getImage();
        prix.setText(String.valueOf(e.getPrix()));
        nom.setText(e.getNom());
        description.setText(e.getDescirption());
        
        imv.setImage(new Image("/spotify/view/img/"+e.getImage()));;

        
        
        
        // TODO
    }    

    @FXML
    private void Upload(ActionEvent event) throws MalformedURLException {
        
        
               FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            imv.setImage(image);
            
            
        
        
    }
        
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLDataException, SQLException {
        
        

       

       Abonnement e = new Abonnement();
       e.setDescirption(description.getText());
       e.setPrix(Integer.parseInt(prix.getText()));
       if(pfile == null){
       e.setImage(abonnementService.get_Abonnement(AffichierAbonnementController.ide).getImage());
       }
       else{
       e.setImage(lien);
       copier(pfile, pDir);
       }
       e.setNom(nom.getText());
       e.setId_cate(categorieService.getCategorieByDescription(chexbox.getValue()).getId_categori());
       abonnementService.addAbonnement(e);
                         Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/spotify/view/AffichierAbonnement.fxml"));
               Stage myWindow = (Stage) prix.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
       
       
        
        
    }
    
    
            
             public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }
    
}
