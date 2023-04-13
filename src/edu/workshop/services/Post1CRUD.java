/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.services;

import edu.worshop.interfaces.PostCRUD;
import edu.worshop.model.Post;

import edu.worshop.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sammo
 */
public class Post1CRUD implements PostCRUD{
    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
    
    @Override
    public void ajouterPost(Post P) {
       try {
            if (P instanceof Post) {
           //likes + dislike
String req = "INSERT INTO `post`(`titre_Post`, `contenu_Post`, `image_Post`, `auteur_Post`) VALUES ('"+P.getTitrePost()+"','"+P.getContenuPost()+"','"+P.getImagePost()+"','"+P.getAuteurPost()+"')";
//date + likes + dislike
//String req = "INSERT INTO `post`(`titre_Post`, `contenu_Post`, `image_Post`, `auteur_Post`, `dateCreation_Post`) VALUES ('"+P.getTitrePost()+"','"+P.getContenuPost()+"','"+P.getImagePost()+"','"+P.getAuteurPost()+"','"+P.getDateCreationPost()+"')";
    
//String req = "INSERT INTO `post`(`titre_Post`, `contenu_Post`, `image_Post`, `auteur_Post`, `dateCreation_Post`) VALUES ('"+P.getTitrePost()+"','"+P.getContenuPost()+"','"+P.getImagePost()+"','"+P.getAuteurPost()+"','"+P.getDateCreationPost()+"')";           
            
ste = conn.createStatement();
            ste.executeUpdate(req);
            } else {
                System.out.println("L'objet passé en paramètre n'est pas un post.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    

    
    
    @Override
    public List<Post> afficherPost() {
       List<Post> list = new ArrayList<>();
        try {
        String req = "SELECT `id`, `titre_Post`, `contenu_Post`, `image_Post`, `auteur_Post`, `likes`, `dislike` FROM `post`";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Post P = new Post();
             P.setId(RS.getInt(1));
             P.setTitrePost(RS.getString(2));
             P.setContenuPost(RS.getString(3)); 
             P.setImagePost(RS.getString(4));    
             P.setAuteurPost(RS.getString(5));       
             //P.setDateCreationPost(RS.getDate(6)); //getDate
             P.setLikes(RS.getInt(6));
             P.setDislike(RS.getInt(7));
             list.add(P);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    @Override
    public void supprimerPost(int id) {
        try {
            String req = "DELETE FROM `post` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Post deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @Override
    public void modifierPost(Post P) {
        try {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String req = "UPDATE `Post` SET `titre_Post` = '" + P.getTitrePost()+ "', `contenu_Post` = '"+ P.getContenuPost()+ "', `image_Post` = '" + P.getImagePost()+ "', `auteur_Post` = '" + P.getAuteurPost()+ "' WHERE `id` = " + P.getId();
        Statement st = conn.createStatement();            
        //  String req = "UPDATE `Post` SET `titre_Post` = '" + P.getTitrePost()+ "', `contenu_Post` = '"+ P.getContenuPost()+ "', `image_Post` = '" + P.getImagePost()+ "', `auteur_Post` = '" + P.getAuteurPost()+ "', `dateCreation_Post` = '" + P.getDateCreationPost()+ "' WHERE `id` = " + P.getId();
            st.executeUpdate(req);
            System.out.println("Post updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}



