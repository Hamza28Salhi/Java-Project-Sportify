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
           //likes + dislike
String req = "INSERT INTO `post`(`titre_Post`, `contenu_Post`, `image_Post`, `auteur_Post`,`Likes`,`Dislike`) VALUES ('"+P.getTitrePost()+"','"+P.getContenuPost()+"','"+P.getImagePost()+"','"+P.getAuteurPost()+"','"+P.getLikes()+"','"+P.getDislike()+"')";
//date + likes + dislike
//String req = "INSERT INTO `post`(`titre_Post`, `contenu_Post`, `image_Post`, `auteur_Post`, `dateCreation_Post`) VALUES ('"+P.getTitrePost()+"','"+P.getContenuPost()+"','"+P.getImagePost()+"','"+P.getAuteurPost()+"','"+P.getDateCreationPost()+"')";
    
//String req = "INSERT INTO `post`(`titre_Post`, `contenu_Post`, `image_Post`, `auteur_Post`, `dateCreation_Post`) VALUES ('"+P.getTitrePost()+"','"+P.getContenuPost()+"','"+P.getImagePost()+"','"+P.getAuteurPost()+"','"+P.getDateCreationPost()+"')";           
            
ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Post ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Post non ajouté");
    
    
}
    }
    
    @Override
    public List<Post> afficherPost() {
       List<Post> list = new ArrayList<>();
        try {
            String req = "Select * from post";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Post P = new Post();
             P.setId(RS.getInt(1));
             P.setTitrePost(RS.getString(2));
             P.setContenuPost(RS.getString(3)); 
             P.setImagePost(RS.getString(4));    
             P.setAuteurPost(RS.getString(5));       
             P.setDateCreationPost(RS.getDate(6)); //getDate
             P.setLikes(RS.getInt(7));
             P.setDislike(RS.getInt(8));
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String req = "UPDATE `Post` SET `titre_Post` = '" + P.getTitrePost()+ "', `contenu_Post` = '"+ P.getContenuPost()+ "', `image_Post` = '" + P.getImagePost()+ "', `auteur_Post` = '" + P.getAuteurPost()+ "', `dateCreation_Post` = '" + sdf.format(P.getDateCreationPost())+ "' WHERE `id` = " + P.getId();
        Statement st = conn.createStatement();            
        //  String req = "UPDATE `Post` SET `titre_Post` = '" + P.getTitrePost()+ "', `contenu_Post` = '"+ P.getContenuPost()+ "', `image_Post` = '" + P.getImagePost()+ "', `auteur_Post` = '" + P.getAuteurPost()+ "', `dateCreation_Post` = '" + P.getDateCreationPost()+ "' WHERE `id` = " + P.getId();
            st.executeUpdate(req);
            System.out.println("Post updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}



