package tn.esprit.tpfoyer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entities.Universite;

public interface UniversiteRepo extends JpaRepository<Universite,Long> {
}
