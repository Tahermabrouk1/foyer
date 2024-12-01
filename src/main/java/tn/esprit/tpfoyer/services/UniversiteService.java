package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Universite;

import java.util.List;
import java.util.Optional;

public interface UniversiteService {
    Universite addUniversite(Universite universite);
    List<Universite> getAllUniversites();
    Optional<Universite> GetUniversiteById(Long id);
    void Delete (Long id);
    Universite Update (Long id, Universite universite);

    Universite affectFoyerToUniversite(Long UniversiteId, Long foyerId);
    Universite desaffectFoyerFromUniversite(Long universeId);
}
