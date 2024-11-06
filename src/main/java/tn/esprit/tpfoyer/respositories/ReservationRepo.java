package tn.esprit.tpfoyer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entities.Reservation;
@Repository

public interface ReservationRepo extends JpaRepository<Reservation,Long> {
}
