package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation addReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Optional<Reservation> GetReservationById(Long id);
    void Delete (Long id);
    Reservation Update (Long id, Reservation reservation);
    Reservation affectEtudiantToReservation(Long idReservation , Long idEtudiant);
    Reservation deaffectEtudiantToReservation(Long idReservation, Long idEtudiant);
}
