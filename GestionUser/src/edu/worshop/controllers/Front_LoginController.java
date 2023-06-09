/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.worshop.model.User;
import edu.workshop.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.captcha.Captcha;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class Front_LoginController implements Initializable {

    @FXML
    private TextField emaillogin;
    @FXML
    private TextField passwordlogin;
    @FXML
    private Label LoginLabel;
    @FXML
    private Hyperlink redirectforgetpasssword;
    @FXML
    private Hyperlink redirectregister;
    @FXML
    private Button connexionF;
    private int passwordAttempts = 0;// variable pour vérifier si l'interface
    @FXML
    private AnchorPane logoo;
    @FXML
    private ImageView cap;
    @FXML
    private ImageView logo;

    @FXML
    private TextField code;
    @FXML
    private ImageView emailicon;
    @FXML
    private ImageView pwdicon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        captcha = setCaptcha();

        redirectforgetpasssword.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Forget_Password.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
                //showAlert("Error loading");
            }

        });

        redirectregister.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Front_Registration.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
                //showAlert("Error loading");
            }

        });
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        LoginLabel.setFont(font);
        connexionF.setFont(buttonfont);
    }

    @FXML
    private void connexionF(ActionEvent event) throws IOException {
        String email = emaillogin.getText();
        String password = passwordlogin.getText();

        // create an instance of the UserService class
        ServiceUser userService = new ServiceUser();
        // call the authenticate method on the UserService instance
        User loggedInUser = userService.authenticate(email, password);

        if (captcha.isCorrect(code.getText())) {
            if (loggedInUser != null) {
                List<String> roles = loggedInUser.getRoles();
                if (roles.contains("[\"ROLE_ADMIN\"]")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/home.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();

                    Notifications.create()
                            .title("Notification")
                            .text("Welcome Back " + loggedInUser.getFull_name() + "!")
                            .position(Pos.BOTTOM_RIGHT)
                            .showInformation();
                } else if (roles.contains("[\"ROLE_BOBO\"]")) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/AfficherProduitBack.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                        Notifications.create()
                                .title("Notification")
                                .text("Welcome Back " + loggedInUser.getFull_name() + "!")
                                .position(Pos.BOTTOM_RIGHT)
                                .showInformation();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (roles.contains("[\"ROLE_USER\"]")) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/Front_Profile.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                        Notifications.create()
                                .title("Notification")
                                .text("Welcome Back " + loggedInUser.getFull_name() + "!")
                                .position(Pos.BOTTOM_RIGHT)
                                .showInformation();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    captcha = setCaptcha();
                    code.setText("");
                }
            }
        } else {
            displayErrorMessage();

            // Bloquer l'interface si l'utilisateur saisit un mot de passe incorrect 3 fois
            passwordAttempts++;
            if (passwordAttempts == 3) {
                connexionF.setDisable(true);
                passwordlogin.setDisable(true);
                emaillogin.setDisable(true);
                LoginLabel.setDisable(true);
                redirectforgetpasssword.setDisable(true);
                code.setDisable(true);
                cap.setOpacity(0);
                emailicon.setOpacity(0);
                pwdicon.setOpacity(0);

                redirectregister.setDisable(true);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            connexionF.setDisable(false);
                            passwordlogin.setDisable(false);
                            emaillogin.setDisable(false);
                            LoginLabel.setDisable(false);
                            redirectforgetpasssword.setDisable(false);
                            code.setDisable(false);
                            cap.setDisable(false);
                            cap.setOpacity(1);
                            passwordAttempts = 0;
                            emailicon.setOpacity(1);
                            pwdicon.setOpacity(1);
                        });
                    }
                }, 60000); // 1 minute en millisecondes
                Notifications.create()
                        .title("Notification")
                        .text("Interface disabled for 60 seconds!")
                        .position(Pos.BOTTOM_RIGHT)
                        .showInformation();
            }
        }
    }

    private void displayErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "plz verify input", ButtonType.OK);
        alert.showAndWait();
    }

    public Captcha setCaptcha() {
        Captcha captchaV = new Captcha.Builder(250, 150)
                .addText()
                .addBackground()
                .addNoise()
                // .gimp()
                .addBorder()
                .build();

        System.out.println(captchaV.getImage());
        Image image = SwingFXUtils.toFXImage(captchaV.getImage(), null);

        cap.setImage(image);

        return captchaV;
    }
    Captcha captcha;

}
