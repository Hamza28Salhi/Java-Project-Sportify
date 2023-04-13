/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.interfaces;

import edu.worshop.model.Reservation;
import java.util.List;

/**
 *
 * @author HOUYEM
 */
public interface ReservationCRUD {
    public void ajouterReservation(Reservation R);
    public List<Reservation> afficherReservation();
    public void supprimerReservation(int id);
    public void modifierReservation(Reservation R);
}
