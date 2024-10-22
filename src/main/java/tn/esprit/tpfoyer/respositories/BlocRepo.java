package tn.esprit.tpfoyer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entities.Bloc;

@Repository
public interface BlocRepo extends JpaRepository<Bloc , Long> {
}
