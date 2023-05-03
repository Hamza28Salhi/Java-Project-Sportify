/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.model;
import java.sql.Date;

/**
 *
 * @author sammo
 */
public class Commentaire { 
    private int id, post_id,id_user;
    private String contenu_Commentaire,auteur_Commentaire;
    //private Date dateCreation_Commentaire;

    public Commentaire(int post_id, String contenu_Commentaire, String auteur_Commentaire,int id_user) {
        this.post_id = post_id;
        this.id_user = id_user;
        this.contenu_Commentaire = contenu_Commentaire;
        this.auteur_Commentaire = auteur_Commentaire;
        //this.dateCreation_Commentaire = dateCreation_Commentaire;
        
    }
    public Commentaire(int id, int post_id,String contenu_Commentaire, String auteur_Commentaire, int id_user) {
        this.id = id;
        this.post_id = post_id;
        this.id_user = id_user;
        this.contenu_Commentaire = contenu_Commentaire;
        this.auteur_Commentaire = auteur_Commentaire;
        //this.dateCreation_Commentaire = dateCreation_Commentaire;
        
    }

    /*public Commentaire(int id, int post_id, String contenu_Commentaire, String auteur_Commentaire, Date dateCreation_Commentaire) {
        this.id = id;
        this.post_id = post_id;
        this.contenu_Commentaire = contenu_Commentaire;
        this.auteur_Commentaire = auteur_Commentaire;
        this.dateCreation_Commentaire = dateCreation_Commentaire;
       
    }*/

    

    
    public Commentaire() {
       
    }

    
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    
    
       

    public String getAuteurCommentaire() {
        return auteur_Commentaire;
    }

    public void setAuteurCommentaire(String auteur_Commentaire) {
        this.auteur_Commentaire = auteur_Commentaire;
    }
    
    
public String getContenuCommentaire() {
        return contenu_Commentaire;
    }

    public void setContenuCommentaire(String contenu_Commentaire) {
        this.contenu_Commentaire = contenu_Commentaire;
    }

  

    /*public Date getDateCreationCommentaire() {
        return dateCreation_Commentaire;
    }

    public void setDateCreationCommentaire(Date dateCreation_Commentaire) {
        this.dateCreation_Commentaire = dateCreation_Commentaire;
    }
*/
    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", post_id=" + post_id + ", contenu_Commentaire=" + contenu_Commentaire + ", auteur_Commentaire=" + auteur_Commentaire;
    }

    

    

        
     
}
