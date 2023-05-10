/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers ;



import edu.worshop.model.Abonnement;
import javafx.scene.control.ListCell;

/**
 *

 */
public class ListViewAbonnment extends ListCell<Abonnement> {
    
    
     @Override
     public void updateItem(Abonnement e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            AbonnementItemController data = new AbonnementItemController();
            data.setInfo(e);
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
        }
    }
    
}
