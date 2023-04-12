/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class GestionUsersController implements Initializable {

    @FXML
    private TableView<?> usersTable;
    @FXML
    private TableColumn<?, ?> editCol;
    @FXML
    private TableColumn<?, ?> emailColumn;
    @FXML
    private TableColumn<?, ?> cinColumn;
    @FXML
    private TableColumn<?, ?> nomColumn;
    @FXML
    private TableColumn<?, ?> prenomColumn;
    @FXML
    private TableColumn<?, ?> numTelephoneColumn;
    @FXML
    private TableColumn<?, ?> dateNaissanceColumn;
    @FXML
    private TableColumn<?, ?> genreColumn;
    @FXML
    private TableColumn<?, ?> createdDateColumn;
    @FXML
    private TableColumn<?, ?> roleColumn;
    @FXML
    private TableColumn<?, ?> activeColumn;
    @FXML
    private TableColumn<?, ?> adresseColumn;
    @FXML
    private TableColumn<?, ?> villeColumn;
    @FXML
    private TableColumn<?, ?> paysColumn;
    @FXML
    private TableColumn<?, ?> numLicenseColumn;
    @FXML
    private TableColumn<?, ?> dateLicenseColumn;
    @FXML
    private TableColumn<?, ?> competencesColumn;
    @FXML
    private TableColumn<?, ?> disponibiliteColumn;
    @FXML
    private TableColumn<?, ?> secteurColumn;
    @FXML
    private ComboBox<?> roleFilterComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
