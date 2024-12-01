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
    void Delete (Long id);
    Foyer Update (Long id, Foyer foyer);
    /**
     * Affecte un bloc existant à un foyer existant.
     * @param blocId l'ID du bloc à affecter.
     * @param foyerId l'ID du foyer auquel le bloc doit être affecté.
     * @return le bloc mis à jour avec la liaison au foyer.
     */
    Foyer affectBlocToFoyer(Long blocId, Long foyerId);

    Foyer desaffectBlocFromFoyer(Long blocId , Long foyerId);
}
