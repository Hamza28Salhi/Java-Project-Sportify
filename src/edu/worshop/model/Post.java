/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.model;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author sammo
 */
public class Post {
    
     private int id, Likes = 0 ,Dislike = 0;
    //private Date dateCreation_Post=new Date(System.currentTimeMillis());
    private String auteur_Post,contenu_Post,titre_Post,image_Post;

    /*public Post(String titre_Post,String contenu_Post,String image_Post,String auteur_Post,Date dateCreation_Post ) {
        this.dateCreation_Post = dateCreation_Post;
        this.auteur_Post = auteur_Post;
        this.contenu_Post = contenu_Post;
        this.image_Post = image_Post;
        this.titre_Post = titre_Post;
        this.Likes = 0;
        this.Dislike = 0;
    }*/
    public Post(String titre_Post,String contenu_Post,String image_Post,String auteur_Post) {
        //this.dateCreation_Post = new Date(System.currentTimeMillis());
        this.auteur_Post = auteur_Post;
        this.contenu_Post = contenu_Post;
        this.image_Post = image_Post;
        this.titre_Post = titre_Post;
    }

    public Post() {
         this.Likes = 0;
         this.Dislike = 0;
     }
    

    public Post(int id,String titre_Post,String contenu_Post,String image_Post,String auteur_Post  ) {
        this.id = id;
        this.auteur_Post = auteur_Post;
        this.contenu_Post = contenu_Post;
        this.titre_Post = titre_Post;
        this.image_Post = image_Post;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

        /*public Date getDateCreationPost() {
        return dateCreation_Post;
    }

    public void setDateCreationPost(Date dateCreation_Post) {
        this.dateCreation_Post = dateCreation_Post;
    }*/

   
    
    public int getLikes(){
        return Likes;
    }

    public void setLikes(int Likes){
        this.Likes = Likes;

    }

    public int getDislike(){
        return Dislike;
    }

    public void setDislike(int Dislike){
        this.Dislike = Dislike;

    }
    
    
    
    

    public String getAuteurPost() {
        return auteur_Post;
    }

    public void setAuteurPost(String auteur_Post) {
        this.auteur_Post = auteur_Post;
    }

    public String getContenuPost() {
        return contenu_Post;
    }

    public void setContenuPost(String contenu_Post) {
        this.contenu_Post = contenu_Post;
    }

    public String getTitrePost() {
        return titre_Post;
    }

    public void setTitrePost(String titre_Post) {
        this.titre_Post = titre_Post;
    }

    public String getImagePost() {
        return image_Post;
    }

    public void setImagePost(String image_Post) {
        this.image_Post = image_Post;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id +", auteur_Post=" + auteur_Post + ", contenu_Post=" + contenu_Post + ", titre_Post=" + titre_Post +  ", nombre de like=" + Likes +  ", nombre de dislike=" + Dislike + ", image_Post=" + image_Post + '}';
    }
    
    
    
    
}


