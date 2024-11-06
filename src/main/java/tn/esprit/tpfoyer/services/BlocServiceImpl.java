package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.respositories.BlocRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements BlocService{
    private BlocRepo BlocRepo;
    @Override
    public Bloc addBloc(Bloc bloc) {
        return BlocRepo.save(bloc);
    }

    @Override
    public List<Bloc> getAllBlocs() {
        return BlocRepo.findAll();
    }

    @Override
    public Optional<Bloc> GetBlocById(Long id) {
        return BlocRepo.findById(id);
    }

    @Override
    public Optional<Bloc> Delete(Long id) {
        Optional<Bloc> bloc = BlocRepo.findById(id);
        bloc.ifPresent(BlocRepo::delete);
        return bloc;
    }

    @Override
    public Bloc Update(Long id, Bloc bloc) {
        Optional<Bloc> existingBloc = BlocRepo.findById(id);
        if (existingBloc.isPresent()) {
            Bloc findbloc = existingBloc.get();
            findbloc.setNomBloc(bloc.getNomBloc());
            findbloc.setCapaciteBloc(bloc.getCapaciteBloc());
            findbloc.setFoyer(bloc.getFoyer());
            return BlocRepo.save(findbloc);
        } else {
            throw new RuntimeException("Bloc non trouvé avec l'ID: " + id);
        }

    }
}
