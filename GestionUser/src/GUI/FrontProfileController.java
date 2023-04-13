/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Utils.Sportify;
import Services.ServiceUser;
/**
 * FXML Controller class
 *
 * @author azizo
 */
import Entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
public class FrontProfileController implements Initializable {

    @FXML
    private TextField full_namet;
    @FXML
    private TextField date_naisst;
    @FXML
    private TextField emailt;
    @FXML
    private TextField addresst;
    ServiceUser su = new ServiceUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int userId = Sportify.getUserId();
        User user = new ServiceUser().getUserById(userId);
        if (user != null) {
        full_namet.setText(user.getFull_name());
        emailt.setText(user.getEmail());
        addresst.setText(user.getAddress());
        date_naisst.setText(user.getDate_naissance().toString());
    }
    }

    @FXML
    private void EditUser(MouseEvent event) {
        
        int userId = Sportify.getUserId();
        User user = new User();
        user.setId(userId);
        user.setEmail(emailt.getText());
        user.setPassword(""); // set empty password to indicate that it should not be changed
        user.setAddress(addresst.getText());
        user.setFull_name(full_namet.getText());
        su.update(user);
    }

}
