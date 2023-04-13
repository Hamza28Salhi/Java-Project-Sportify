/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop3a4;

import edu.workshop.services.Evenement1CRUD;
import edu.workshop.services.Reservation1CRUD;
import edu.worshop.model.Evenement;
import edu.worshop.model.Reservation;
import java.sql.Date;

/**
 *
 * @author belkn
 */
public class Workshop3A4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       /// MyConnection conn = MyConnection.getInstance();
        //Evenement E1 = new Evenement(Date.valueOf("2023-1-1"), "ababababababa", "zzzzzzzz", "aaaaaaa", " ", "sportify" );
         //Reservation R1 = new Reservation(21, "par virement", "houyem", "kaaniche", "houyem@gmail.com", "12345678" );
       //Evenement1CRUD event = new Evenement1CRUD();
        Reservation1CRUD res = new Reservation1CRUD();
        
        //event.ajouterEvenement(E1);
        //res.ajouterReservation(R1);
        
        //System.out.println( event.afficherEvenement());
        //System.out.println( res.afficherReservation());
        
        //event.supprimerEvenement(19);
       // res.supprimerReservation(26);
       
        //event.modifierEvenement(new Evenement(32,Date.valueOf("2023-04-13"), "houyem", "houyem", "aaabbbbbaaaa", " ", "sportify" ));
        //res.modifierReservation(new Reservation(30,16, "par espèces", "houyemaaaaaaaaaa", "kaaniche", "houyem@gmail.com", "12345678" ));
          
       
        
    }
    
}
