package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.respositories.ReservationRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepo reservationRepo;
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
    public Optional<Reservation> Delete(Long id) {
        Optional<Reservation> reservation = reservationRepo.findById(id);
        reservation.ifPresent(reservationRepo::delete);
        return reservation;
    }

    @Override
    public Reservation Update(Long id, Reservation reservation) {
        Optional<Reservation> existingFoyer = reservationRepo.findById(id);

        if (existingFoyer.isPresent()) {
            Reservation findReservation = existingFoyer.get();
            findReservation.setEtudiants(reservation.getEtudiants());
            findReservation.setAnneeUniversitaire(reservation.getAnneeUniversitaire());
            return reservationRepo.save(findReservation);
        } else {
            throw new RuntimeException("Bloc non trouvé avec l'ID: " + id);
        }
    }
}
