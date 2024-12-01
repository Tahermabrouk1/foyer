package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Etudiant;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.respositories.EtudiantRepo;

import java.util.List;
import java.util.NoSuchElementException;
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

    @Override
    public void Delete(Long id) {
        Etudiant etudiant = etudiantRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Etudiant not found with ID: " + id));
        etudiantRepo.delete(etudiant);
    }

    @Override
    public Etudiant Update(Long id, Etudiant etudiant) {
        Optional<Etudiant> existingEtudiant = etudiantRepo.findById(id);
        if (existingEtudiant.isPresent()) {
            Etudiant findEtudiant = existingEtudiant.get();
            findEtudiant.setCin(etudiant.getCin());
            findEtudiant.setEcole(etudiant.getEcole());
            findEtudiant.setNomEt(etudiant.getNomEt());
            findEtudiant.setDateNaissance(etudiant.getDateNaissance());
            findEtudiant.setPrenomEt(etudiant.getPrenomEt());
            return etudiantRepo.save(findEtudiant);
        } else {
            throw new RuntimeException("Etudiant non trouvé avec l'ID: " + id);
        }
    }
}
