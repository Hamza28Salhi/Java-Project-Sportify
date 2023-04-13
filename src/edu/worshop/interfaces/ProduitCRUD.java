/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.interfaces;

import edu.worshop.model.Produit;
import java.util.List;
/**
 *
 * @author lenovo
 */
public interface ProduitCRUD {
      public void ajouterProduit(Produit P);
    public List<Produit> afficherProduit();
    public void supprimerProduit(int id);
    public void modifierProduit(Produit P);
    
}
