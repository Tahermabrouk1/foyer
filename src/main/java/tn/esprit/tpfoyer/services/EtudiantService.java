package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Etudiant;

import java.util.List;
import java.util.Optional;

public interface EtudiantService {
    Etudiant addEtudiant(Etudiant etudiant);
    List<Etudiant> getAllEtudiants();
    Optional<Etudiant> GetEtudiantById(Long id);
    Optional<Etudiant> Delete (Long id);
    Etudiant Update (Long id, Etudiant etudiant);
}
