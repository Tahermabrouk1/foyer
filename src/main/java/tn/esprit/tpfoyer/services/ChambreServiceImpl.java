package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Bloc;
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
    @Override
    public Optional<Chambre> Delete(Long id) {
        Optional<Chambre> chambre = chambreRepo.findById(id);
        chambre.ifPresent(chambreRepo::delete);
        return chambre;
    }

    @Override
    public Chambre Update(Long id, Chambre chambre) {
        Optional<Chambre> existingChambre = chambreRepo.findById(id);
        if (existingChambre.isPresent()) {
            Chambre findChambre = existingChambre.get();
            findChambre.setNumeroChambre(chambre.getNumeroChambre());
            findChambre.setBloc(chambre.getBloc());
            findChambre.setTypeC(chambre.getTypeC());
            return chambreRepo.save(findChambre);
        } else {
            throw new RuntimeException("Chambre non trouvé avec l'ID: " + id);
        }
    }
}
