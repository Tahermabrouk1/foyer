package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Etudiant;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.respositories.EtudiantRepo;
import tn.esprit.tpfoyer.respositories.ReservationRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepo reservationRepo;
    private EtudiantRepo etudiantRepo;
    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();
    }

    @Override
    public Optional<Reservation> GetReservationById(Long id) {
        return reservationRepo.findById(id);
    }

    @Override
    public void Delete(Long id) {
        Reservation reservation = reservationRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reservation not found with ID: " + id));
        reservationRepo.delete(reservation);
    }


    @Override
    public Reservation Update(Long id, Reservation reservation) {
        Optional<Reservation> existingFoyer = reservationRepo.findById(id);

        if (existingFoyer.isPresent()) {
            Reservation findReservation = existingFoyer.get();
            findReservation.setEtudiants(reservation.getEtudiants());
            findReservation.setAnneeUniversitaire(reservation.getAnneeUniversitaire());
            findReservation.setEstValide(reservation.isEstValide());
            return reservationRepo.save(findReservation);
        } else {
            throw new RuntimeException("Bloc non trouvé avec l'ID: " + id);
        }
    }

    @Override
    public Reservation affectEtudiantToReservation(Long idReservation, Long idEtudiant) {
        Reservation reservation = reservationRepo.findById(idReservation).orElseThrow(() -> new NoSuchElementException("introuvable"));
        Etudiant etudiant = etudiantRepo.findById(idEtudiant).orElseThrow(() -> new NoSuchElementException("introuvable"));
        reservation.getEtudiants().add(etudiant);
        return reservationRepo.save(reservation);
    }

    @Override
    public Reservation deaffectEtudiantToReservation(Long idReservation, Long idEtudiant) {
        Reservation reservation = reservationRepo.findById(idReservation).orElseThrow(() -> new NoSuchElementException("introuvable"));
        Etudiant etudiant = etudiantRepo.findById(idEtudiant).orElseThrow(() -> new NoSuchElementException("introuvable"));
        reservation.getEtudiants().remove(etudiant);
        return reservationRepo.save(reservation);
    }
}
