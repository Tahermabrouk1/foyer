package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Universite;
import tn.esprit.tpfoyer.respositories.UniversiteRepo;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements UniversiteService{
    private UniversiteRepo universiteRepo;
    @Override
    public Universite addUniversite(Universite universite) {
        return universiteRepo.save(universite);
    }

    @Override
    public List<Universite> getAllUniversites() {
        return universiteRepo.findAll();
    }

    @Override
    public Optional<Universite> GetUniversiteById(Long id) {
        return universiteRepo.findById(id);
    }
}
