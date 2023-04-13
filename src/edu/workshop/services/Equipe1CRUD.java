/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.services;
import edu.worshop.interfaces.EquipeCRUD;
import edu.worshop.model.Equipe;

import edu.worshop.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ace River
 */
public class Equipe1CRUD implements EquipeCRUD{
    
    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
   
    public void ajouterEquipe(Equipe E) {
       try {
            String req = "INSERT INTO `equipe`( `nom`, `joueurs`, `classement`, `entraineur`, `categorie`, `picture`) VALUES ('"+E.getNom()+"','"+E.getJoueurs()+"','"+E.getClassement()+"','"+E.getEntraineur()+"','"+E.getCategorie()+"','"+E.getPicture()+"')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Equipe ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Equipe non ajouté");
    
    
}
    }
    
      
    public List<Equipe> afficherEquipe() {
       List<Equipe> list = new ArrayList<>();
        try {
            String req = "Select * from equipe";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Equipe E = new Equipe();
             E.setNom(RS.getString(2));
             E.setId(RS.getInt(1));
             E.setJoueurs(RS.getString(3));
             E.setClassement(RS.getInt(4));
             E.setEntraineur(RS.getString(5));
             E.setCategorie(RS.getString(6));
             E.setPicture(RS.getString(7));
             list.add(E);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
     
   
    public void supprimerEquipe(int id) {
        try {
            String req = "DELETE FROM `equipe` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Equipe deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     
    public void modifierEquipe(Equipe E) {
        try {
            String req = "UPDATE `Equipe` SET `nom` = '" + E.getNom()+ "', `joueurs` = '" + E.getJoueurs()+ "', `classement` = '" + E.getClassement()+ "', `entraineur` = '" + E.getEntraineur()+ "', `categorie` = '" + E.getCategorie()+ "', `picture` = '" + E.getPicture()+ "' WHERE `equipe`.`id` = " + E.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Equipe updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }     
}


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

