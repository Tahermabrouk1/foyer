package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Etudiant;
import tn.esprit.tpfoyer.respositories.EtudiantRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements EtudiantService{
    private EtudiantRepo etudiantRepo;
    @Override
    public Etudiant addEtudiant(Etudiant etudiant) {
        return etudiantRepo.save(etudiant);
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepo.findAll();
    }

    @Override
    public Optional<Etudiant> GetEtudiantById(Long id) {
        return etudiantRepo.findById(id);
    }
}
