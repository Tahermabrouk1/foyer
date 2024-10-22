package tn.esprit.tpfoyer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entities.Reservation;

public interface ReservationRepo extends JpaRepository<Reservation,Long> {
}
