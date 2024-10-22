package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
}
