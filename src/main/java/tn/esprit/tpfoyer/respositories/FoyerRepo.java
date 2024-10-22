package tn.esprit.tpfoyer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entities.Foyer;

public interface FoyerRepo extends JpaRepository<Foyer,Long> {
}
