package tn.esprit.tpfoyer.services;

import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Foyer;

import java.util.List;
import java.util.Optional;
public interface FoyerService {
    Foyer addFoyer(Foyer foyer);
    List<Foyer> getAllFoyers();
    Optional<Foyer> GetFoyerById(Long id);
    Optional<Foyer> Delete (Long id);
    Foyer Update (Long id, Foyer foyer);
}
