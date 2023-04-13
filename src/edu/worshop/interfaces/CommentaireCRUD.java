/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.interfaces;

import edu.worshop.model.Commentaire;
import java.util.List;

/**
 *
 * @author sammo
 */
public interface CommentaireCRUD {
    public void ajouterCommentaire(Commentaire R);
    public List<Commentaire> afficherCommentaire();
    public void supprimerCommentaire(int id);
    public void modifierCommentaire(Commentaire R);
    public List<Commentaire> afficherCommentaireParPostId(int idPost);
    
}
