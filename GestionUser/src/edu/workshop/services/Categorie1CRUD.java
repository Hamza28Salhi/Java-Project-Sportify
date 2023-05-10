/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.services;

import edu.worshop.interfaces.CategorieCRUD;
import edu.worshop.model.Categorie;

import edu.worshop.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class Categorie1CRUD  implements CategorieCRUD{
    
     Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
    
    @Override
    public void ajouterCategorie(Categorie C) {
       try {
            String req = "INSERT INTO `categorie`( `nom_categorie`) VALUES ('"+C.getNom_categorie()+"')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Categorie ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Categorie non ajouté");
    
}
}
    
    @Override
    public List<Categorie> afficherCategorie() {
       List<Categorie> list = new ArrayList<>();
        try {
            String req = "Select * from categorie";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Categorie C = new Categorie();
             C.setNom_categorie(RS.getString(2));
             C.setId(RS.getInt(1));
             
             list.add(C);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
       @Override
    public void supprimerCategorie(int id) {
        try {
            String req = "DELETE FROM `categorie` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @Override
    public void modifierCategorie(Categorie C) {
        try {
            String req = "UPDATE `Categorie` SET `nom_categorie` = '" + C.getNom_categorie() + "' WHERE `categorie`.`id` = " + C.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
