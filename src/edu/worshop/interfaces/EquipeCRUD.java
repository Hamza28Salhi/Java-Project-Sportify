/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.interfaces;

import edu.worshop.model.Equipe;
import java.util.List;

/**
 *
 * @author Ace River
 */
public interface EquipeCRUD {

     public void ajouterEquipe(Equipe E);
     public List<Equipe> afficherEquipe();
     public void supprimerEquipe(int id);
     public void modifierEquipe(Equipe E);
                
}
