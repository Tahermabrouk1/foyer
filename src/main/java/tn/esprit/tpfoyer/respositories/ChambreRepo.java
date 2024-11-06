package tn.esprit.tpfoyer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entities.Chambre;

@Repository
public interface ChambreRepo extends JpaRepository<Chambre,Long> {
}
