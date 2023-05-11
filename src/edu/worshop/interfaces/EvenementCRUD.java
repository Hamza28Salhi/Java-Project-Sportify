/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.interfaces;

import edu.worshop.model.Evenement;
import java.util.List;

/**
 *
 * @author HOUYEM
 */
public interface EvenementCRUD {
    public void ajouterEvenement(Evenement E);
    public List<Evenement> afficherEvenement();
     public void supprimerEvenement(int id);
     public void modifierEvenement(Evenement E);
    
}
