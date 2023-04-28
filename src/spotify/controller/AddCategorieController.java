/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotify.controller;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import spotify.entities.Categorie;
import spotify.services.CategorieService;

/**
 * FXML Controller class
 
 */
public class AddCategorieController implements Initializable {

    @FXML
    private TextField des;
    
    CategorieService categorieService = new CategorieService();

        @FXML
    private Button btnouvrir;
        @FXML
    private ImageView im_qrcode;
            private String deriterio;
    private  static final String DIR ="QRDir";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
           deriterio= new File("").getAbsolutePath();
        deriterio+= File.separator +DIR;
        File file=new File(deriterio);
        if(!file.isDirectory()){
        file.mkdir();}
    }    
    
        
       public String readQRCode(String filePath, String charset, Map hintMap)throws FileNotFoundException, IOException, NotFoundException{
        BinaryBitmap binaryBitmap= new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath)))));
        Result qrCodeResult= new MultiFormatReader().decode(binaryBitmap,hintMap);
        return qrCodeResult.getText();
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        
        Categorie c = new Categorie();
        
        c.setDescription(des.getText());
        
        
        if (des.getText().equals("") ){

                 Alert alert = new Alert(Alert.AlertType.ERROR, "vérifier votre champs", ButtonType.OK);
           alert.showAndWait();
        
       
        }
        else{
        
                            categorieService.addCategorie(c);

                 Notifications.create().title("Success").text("Ajouter Avex succes").position(Pos.BOTTOM_RIGHT).showError();

        
        Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/spotify/view/AfficherCategorie.fxml"));
               Stage myWindow = (Stage) des.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
    }
        }
    
        
        @FXML
    private void ouvrir(ActionEvent event) {
         FileChooser  fileChooser =new FileChooser();
        fileChooser.setInitialDirectory(new File(deriterio));
        FileChooser.ExtensionFilter extfilterpng= new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        FileChooser.ExtensionFilter extfilterJPG= new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
         FileChooser.ExtensionFilter extfilterjpg= new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
          FileChooser.ExtensionFilter extfilterPNG= new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
         fileChooser.getExtensionFilters().addAll(extfilterpng, extfilterJPG, extfilterjpg, extfilterPNG);
         File file=fileChooser.showOpenDialog(null);
         if(file!= null){
            try {
                Image image= new Image(new FileInputStream(file.getAbsolutePath()));
                im_qrcode.setImage(image);
                 String charset="UTF-8";
            Map hintMap= new HashMap();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            String readQRCode= readQRCode(file.getAbsolutePath(),charset, hintMap);
            des.setText(readQRCode.substring(readQRCode.indexOf("description:"), readQRCode.indexOf("adresse:")));
          
              
            } catch (IOException | NotFoundException ex) {
                System.out.println(ex); 
            }

         }
  }
    
}
