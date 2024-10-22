package tn.esprit.tpfoyer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entities.Etudiant;

public interface EtudiantRepo extends JpaRepository<Etudiant,Long> {
}
