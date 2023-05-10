/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.services;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.time.LocalDate;

/**
 *
 * @author Ace River
 */
public class Mailservice {
     public void sendMail(String recipient, String date, String nom,String stade) {
        System.out.println(recipient);
        /*String host="ranim.ahmadi@esprit.tn";
        final String user="ranim.ahmadi@esprit.tn";//← my email address
        final String password="201JFT2214";//change accordingly   //← my email password
         */
        String host = "hamza.salhi@esprit.tn";  //← my email address
        final String user = "hamza.salhi@esprit.tn";//← my email address
        final String password = "theaceking11";//change accordingly   //← my email password

        String to = recipient;//→ the EMAIL i want to send TO →
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        //My message :
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(" Notification Match!!! ");
            //Text in emial :
            //message.setText("  → Text message for Your Appointement ← ");
            //Html code in email :
            message.setContent(
                    "<h1 style=\"color:blue\">La date du match est : " + date + "</h1>"
+ "<br/><h2>Nom du match : " + nom + "</h2></br>"
+ "<p>Stade : " + stade + "</p>"
+ "<img width=\"50%\" height=\"50%\" src=\"https://i.imgur.com/HL4euq4.jpeg\">"
,
                    "text/html");

            //send the message
            Transport.send(message);

            System.out.println("message sent successfully via mail ... !!! ");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}

//     String message ="message";
//     
//
////...
//
//// Obtention de la date actuelle
//LocalDate currentDate = LocalDate.now();
//
//// Affichage de la date dans le format JJ/MM/YYYY
//String formattedDate = currentDate.getDayOfMonth() + "/" + currentDate.getMonthValue() + "/" + currentDate.getYear();
//
//
//    private void sendEmail() {
//    // Récupérer l'adresse email et le mot de passe pour le compte que vous utiliserez pour envoyer l'email
//    String username = "votre-adresse-email";
//    String password = "votre-mot-de-passe-email";
//
//    // Définir l'adresse et le port du serveur SMTP pour votre fournisseur de services email
//    String smtpHost = "smtp.votre-fournisseur-de-services-email.com";
//    int smtpPort = 587;
//
//    // Définir le destinataire, l'objet et le message pour l'email
//    String to = toField.getText();
//    String subject = subjectField.getText();
//    String message = messageField.getText();
//
//    // Créer un objet Properties pour contenir les paramètres du serveur SMTP
//    Properties props = new Properties();
//    props.put("mail.smtp.auth", "true");
//    props.put("mail.smtp.starttls.enable", "true");
//    props.put("mail.smtp.host", smtpHost);
//    props.put("mail.smtp.port", smtpPort);
//
//    // Créer un objet Session pour authentifier le compte email
//    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//        protected PasswordAuthentication getPasswordAuthentication() {
//            return new PasswordAuthentication(username, password);
//        }
//    });
//
//    try {
//        // Créer un objet MimeMessage pour représenter le message email
//        Message mimeMessage = new MimeMessage(session);
//        mimeMessage.setFrom(new InternetAddress(username));
//        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
//        mimeMessage.setSubject(subject);
//        mimeMessage.setText(message);
//
//        // Envoyer le message email
//        Transport.send(mimeMessage);
//
//        // Mettre à jour le label de statut pour indiquer que l'email a été envoyé avec succès
//        statusLabel.setText("Email envoyé avec succès");
//    } catch (MessagingException e) {
//        // Mettre à jour le label de statut pour indiquer qu'il y a eu une erreur lors de l'envoi de l'email
//        statusLabel.setText("Erreur lors de l'envoi de l'email");
//        e.printStackTrace();
//    }
//}
//    }
/* String to = "marwen.mamlouk@esprit.tn";//change accordingly  
      String from = "mamloukmarwen219@gmail.com";//change accordingly  
      String host = "localhost";//or IP address  
  
     //Get the session object  
     Properties properties = System.getProperties();
properties.put("mail.smtp.auth", "true");
properties.put("mail.smtp.starttls.enable", "true");
properties.put("mail.smtp.host", "smtp.office365.com");
properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator(){
        
            protected PasswordAuthentication getPasswordAuthentication(){
                return new  PasswordAuthentication("marwen.mamlouk@outlook.com","Azerty123456-");
            }
        });
     // Session session = Session.getDefaultInstance(properties);  
  
     //compose the message  
      try{  
         MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress("mamloukmarwen219@gmail.com"));
            m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject("rbehet frigiere");
            m.setText("KOS OMK");
            
            Transport.send(m);
            
            System.out.println("sent");
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
   }  */
    

