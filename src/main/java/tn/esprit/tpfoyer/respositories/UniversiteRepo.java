package tn.esprit.tpfoyer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entities.Universite;
@Repository

public interface UniversiteRepo extends JpaRepository<Universite,Long> {
}
