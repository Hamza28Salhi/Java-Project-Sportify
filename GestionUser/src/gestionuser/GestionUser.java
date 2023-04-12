/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser;

import Entities.User;
import Services.ServiceUser;
import java.util.List;

/**
 *
 * @author azizo
 */
public class GestionUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        User p = new User();
        ServiceUser sp = new ServiceUser();
        //User s = new User("yacine2.khouini@gmail.com", "adress", "pass", "yacine khouini");
       //User s = new User("aziz.mansour@gmail.com","address","password", "mohamed aziz mansour");
       //sp.add(s);
       
       
        sp.modifier(new User( "aziz.mansour@gmail.com", "adress", "pass", "yacine khouini"));
        //sp.supprimer(new User("yacine.khouini@gmail.com"));
    }

}
