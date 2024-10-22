package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.entities.Universite;

import java.util.List;
import java.util.Optional;

public interface UniversiteService {
    Universite addUniversite(Universite universite);
    List<Universite> getAllUniversites();
    Optional<Universite> GetUniversiteById(Long id);
}
