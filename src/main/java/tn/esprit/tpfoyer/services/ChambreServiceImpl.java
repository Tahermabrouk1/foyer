package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.respositories.ChambreRepo;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ChambreServiceImpl implements ChambreService{
    private ChambreRepo chambreRepo;
    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepo.save(chambre);
    }

    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepo.findAll();
    }

    @Override
    public Optional<Chambre> GetChambreById(Long id) {
        return chambreRepo.findById(id);
    }
}
