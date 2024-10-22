package tn.esprit.tpfoyer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entities.Chambre;

public interface ChambreRepo extends JpaRepository<Chambre,Long> {
}
