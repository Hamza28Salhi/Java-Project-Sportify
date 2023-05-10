/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Emailsender1;
import edu.workshop.services.Evenement1CRUD;
import edu.workshop.services.Reservation1CRUD;
import edu.worshop.model.Evenement;
import edu.worshop.model.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import static jdk.nashorn.internal.objects.NativeJava.to;
import org.controlsfx.control.Notifications;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import edu.worshop.controllers.AffichageEvenementBackController;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HOUYEM
 */
public class AjoutReservationFrontController implements Initializable {

    @FXML
    private ChoiceBox<String> PaiementRfx;
    @FXML
    private TextField TelephoneRfx;
    @FXML
    private TextField AdresseRfx;
    @FXML
    private TextField PrenomRfx;
    @FXML
    private TextField NomRfx;
     private final String[] PaiementRfxVariable = {"Par chèque", "Par virement", "Par espèces"};
    //private TextField evenement_idfx;
    Evenement E;
    static int evenement_id;
    @FXML
    private Label labeltel;
    private boolean verificationNumero;
    public static final String ACCOUNT_SID = "AC5302b42dfae4fa1d672f424fe8987445";
    public static final String AUTH_TOKEN = "0f66a59ad7987fb4879957ecdd29d7bc";
    @FXML
    private Text label;
    
    
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PaiementRfx.getItems().addAll(PaiementRfxVariable);
        //Evenement_idRfx.getItems().addAll(Evenement_idRfxVariable);
    }   
    
    public static void sendSms(String recipient, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(recipient), // To number
                new PhoneNumber("+12708120292"), // From number
                messageBody) // SMS body
                .create();

        System.out.println("Message sent: " + message.getSid());
    }

    @FXML
    private void AjoutReservationFront(ActionEvent event) {
        //String evenement_id = Evenement_idRfx.getValue();
        String paiement = PaiementRfx.getValue();
        String telephone = TelephoneRfx.getText();
        String adresse = AdresseRfx.getText();
        String prenom = PrenomRfx.getText();
        String nom = NomRfx.getText();
        
        int minLength1 = 4;
        
        if (nom.isEmpty()) {
            //showAlert("le nom est vide ");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "le nom est vide!", ButtonType.OK);
        alert.showAndWait();
         }else if (!nom.matches(".*([a-zA-Z])+")) {
            //showAlert("le nom doit être alphabétique ");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "le nom doit être alphabétique!", ButtonType.OK);
        alert.showAndWait();
         }else if (nom.matches(".*([a-zA-Z])\\1{2,}.*")) {
            //showAlert("La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement.");
         Alert alert = new Alert(Alert.AlertType.INFORMATION, "La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement!", ButtonType.OK);
        alert.showAndWait();    
         }else if (nom.length() < minLength1) {
            //showAlert("Le type doit contenir au moins " + minLength1 + " caractères"); 
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le type doit contenir au moins 4  caractères!", ButtonType.OK);
        alert.showAndWait();
             }
             
             else  if (prenom.isEmpty()) {
            //showAlert("le prénom est vide ");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "le prénom est vide!", ButtonType.OK);
        alert.showAndWait();
         }else if (!prenom.matches(".*([a-zA-Z])+")) {
            //showAlert("le prénom doit être alphabétique ");
         Alert alert = new Alert(Alert.AlertType.INFORMATION, "le prénom doit être alphabétique!", ButtonType.OK);
        alert.showAndWait();
         }else if (prenom.matches(".*([a-zA-Z])\\1{2,}.*")) {
            //showAlert("La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement.");
         Alert alert = new Alert(Alert.AlertType.INFORMATION, "La saisie ne doit pas contenir des caractères alphabétiques répétés plus de deux fois consécutivement!", ButtonType.OK);
        alert.showAndWait();    
         }else if (prenom.length() < minLength1) {
            //showAlert("Le prénom doit contenir au moins " + minLength1 + " caractères"); 
             Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le prénom doit contenir au moins 4 caractères!", ButtonType.OK);
        alert.showAndWait();
             }else if (verifEmail(AdresseRfx)){
             //showAlert("l'email est invalide!");
             Alert alert = new Alert(Alert.AlertType.INFORMATION, "l'email est invalide!", ButtonType.OK);
        alert.showAndWait();
             }else if (verificationNumero) {
             //showAlert("Le numero est invalide");
             Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le numero est invalide!", ButtonType.OK);
        alert.showAndWait();
               
             }else{
        
         
    
    
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("êtes-vous sûr d'ajouter cette réservation  ?");
Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)  {

                    
              Reservation R = new Reservation(AffichageEvenementBackController.E.getId(), paiement, nom, prenom, adresse, telephone);
                    Reservation1CRUD res1 = new Reservation1CRUD();
                    res1.ajouterReservation(R);
                    //showAlert("Réservation ajoutée avec succès");
                     System.out.println("Réservation ajoutée avec succès");
                     sendSms("+21624563142", "une réservation a été ajouté ");
                      
                     
                     String message = "Dear [Client's Name],\n"
                        + "\n"
                        + "I am writing this email to confirm your location reservation for the following details:\n"
                        + "\n"
                        + "nom  : " + nom  + "\n"
                        + "prenom  : " + prenom + "\n"
                        + "adresse  : " + adresse  + "\n"
                        + "telephone  : " + telephone + "\n"
                        + "paiement  : " + paiement  + "\n"
                       
                        //+ "We are pleased to inform you that your reservation has been successfully processed, and we have reserved the required number of seats for you. Your confirmation number is [Enter confirmation number].\n"
                        + "\n";

                Emailsender1.sendEmail_add("houyem.kaaniche@esprit.tn", message);
                
                 Notifications notificationBuilder = Notifications.create()
                .title("réservation ajoutée")
                .text("saved")
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
                 notificationBuilder.darkStyle();
                 notificationBuilder.show();
                 
              
    

try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AfficherReservationBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
                }}
    /* private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }*/
    // Show a success message
       // Alert alert = new Alert(Alert.AlertType.INFORMATION, "thank you for joining Sportify!", ButtonType.OK);
       // alert.showAndWait();
    }
    
private boolean verifEmail(TextField chaine) {
        String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(email_pattern);
        Matcher matcher = pattern.matcher(chaine.getText().trim());

        if (((Matcher) matcher).matches()) {       //if   matcher ne contient pas la format
            return false;

           // verificationUserEmail = true;

        } else {
            return true;
            // JOptionPane.showMessageDialog(null, "Email Format invalide");
            //verificationUserEmail = false;

        }}
       @FXML
    private void veriftel(KeyEvent event) {
    if (TelephoneRfx.getText().trim().length() == 8) {
        int nbChar = 0;
        for (int i = 1; i < TelephoneRfx.getText().trim().length(); i++) {
            char ch = TelephoneRfx.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
          
        }

        if (nbChar == 0) {
            labeltel.setText("number valide");
            //labeltel.setTextFill(Color.GREEN);

            verificationNumero = false;
        } else {
            labeltel.setText("invalide number \n"
                    + " Il exist des char");
            verificationNumero = true;

        }

    } else {
        labeltel.setText("Il faut 8 chiffres");
       verificationNumero = true;
    }
}

    @FXML
    private void redirecttoevenement(ActionEvent event) {
        try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/AffichageEvenementBack.fxml"));
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



 
    

    
