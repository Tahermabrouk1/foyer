package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation addReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Optional<Reservation> GetReservationById(Long id);
    Optional<Reservation> Delete (Long id);
    Reservation Update (Long id, Reservation reservation);
}
