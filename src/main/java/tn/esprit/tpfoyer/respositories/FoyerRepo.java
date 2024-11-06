package tn.esprit.tpfoyer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entities.Foyer;
@Repository

public interface FoyerRepo extends JpaRepository<Foyer,Long> {
}
