/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.interfaces;

import edu.worshop.model.Post;
import java.util.List;

/**
 *
 * @author sammo
 */
public interface PostCRUD {
    public void ajouterPost(Post P);
    public List<Post> afficherPost();
    public int recupererNbrRating(Post P);
    public int recupererRating(Post P);
    public List<Post> afficherrPost();
     public void supprimerPost(int id);
     public void modifierPost(Post P);
    
    
}

