/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.interfaces;

import edu.worshop.model.Categorie;
import java.util.List;
/**
 *
 * @author lenovo
 */
public interface CategorieCRUD {
      public void ajouterCategorie(Categorie C);
   public List<Categorie> afficherCategorie();
    public void supprimerCategorie(int id);
    public void modifierCategorie(Categorie C);
    
}
