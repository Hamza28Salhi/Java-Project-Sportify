/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.services;
import edu.worshop.interfaces.CommentaireCRUD;


import edu.worshop.model.Commentaire;

import edu.worshop.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sammo
 */
public class Commentaire1CRUD implements CommentaireCRUD{
    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
    
    @Override
    public void ajouterCommentaire(Commentaire C) {
       try {
            String req = "INSERT INTO `commentaire`( `post_id`, `contenu_Commentaire`, `auteur_Commentaire`, `id_user_id`) VALUES ('"+C.getPost_id()+"', '"+C.getContenuCommentaire()+"', '"+C.getAuteurCommentaire()+"','"+C.getId_user()+"')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Commentaire ajoutée!!!");
        } catch (SQLException ex) {
            System.out.println("Commentaire non ajoutée"+C.getId_user());
    
    
}
    }
    
      @Override
    public List<Commentaire> afficherCommentaire() {
       List<Commentaire> list = new ArrayList<>();
        try {
            String req = "Select * from commentaire";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Commentaire C = new Commentaire();
             
             C.setId(RS.getInt(1));
             C.setPost_id(RS.getInt(2));
             C.setContenuCommentaire(RS.getString(3));
             C.setAuteurCommentaire(RS.getString(4));
             C.setId_user(5);
             list.add(C);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    
    @Override
    public List<Commentaire> afficherCommentaireParPostId(int postId) {
   List<Commentaire> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM commentaire WHERE post_id = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, postId);
       
        ResultSet RS= ps.executeQuery();
        while(RS.next()){
         Commentaire C = new Commentaire();
         C.setId(RS.getInt(1));
         C.setPost_id(RS.getInt(2));
         C.setContenuCommentaire(RS.getString(3));
         C.setAuteurCommentaire(RS.getString(4));
         list.add(C);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}
    
    @Override
    public void supprimerCommentaire(int id) {
        try {
            String req = "DELETE FROM `commentaire` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Commentaire deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   @Override
    public void modifierCommentaire(Commentaire C) {
        try {
            String req = "UPDATE `Commentaire` SET `post_id` = '"+C.getPost_id()+"', `contenu_Commentaire` = '" + C.getContenuCommentaire()+ "', `auteur_Commentaire` = '" + C.getAuteurCommentaire()+ "', `id_user_id` = '" + C.getId_user()+"' WHERE `commentaire`.`id` = " + C.getId();
            //String req = "UPDATE `Commentaire` SET `post_id` = '"+C.getPost_id()+"', `contenu_Commentaire` = '" + C.getContenuCommentaire()+ "', `auteur_Commentaire` = '" + C.getAuteurCommentaire()+ "', `id_user_id ` = '" + C.getId_user()+"' WHERE `commentaire`.`id` = " + C.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Commentaire updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Commentaire n'est pas modifié et id user est:"+C.getId_user());
        }
    }
    
    
    
}
