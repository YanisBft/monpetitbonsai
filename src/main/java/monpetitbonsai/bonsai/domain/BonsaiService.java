package monpetitbonsai.bonsai.domain;

import monpetitbonsai.bonsai.infrastructure.BonsaiRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BonsaiService {
    private BonsaiRepository bonsaiRepository;

    public BonsaiService(BonsaiRepository bonsaiRepository) {
        this.bonsaiRepository = bonsaiRepository;
    }

    public List<Bonsai> findAll() {
        return bonsaiRepository.findAll();
    }

    public Optional<Bonsai> findById(UUID id) {
        return bonsaiRepository.findById(id);
    }

    public Bonsai create(Bonsai bonsai) {
        return bonsaiRepository.create(bonsai);
    }

    public Optional<Bonsai> update(UUID id, Bonsai updatedBonsai) {
        Optional<Bonsai> bonsai = bonsaiRepository.findById(id);
        if (bonsai.isPresent()) {
            bonsai.get().setName(updatedBonsai.getName());
            bonsai.get().setSpecies(updatedBonsai.getSpecies());
            bonsai.get().setAcquisition_date(updatedBonsai.getAcquisition_date());
            bonsai.get().setAcquisition_age(updatedBonsai.getAcquisition_age());
            return Optional.of(bonsaiRepository.update(bonsai.get()));
        }
        return bonsai;
    }

    public Optional<Bonsai> updateStatus(UUID id, String status) {
        Optional<Bonsai> bonsai = bonsaiRepository.findById(id);
        if (bonsai.isPresent()) {
            bonsai.get().setStatus(status);
            return Optional.of(bonsaiRepository.updateStatus(bonsai.get()));
        }
        return bonsai;
    }

    public void delete(UUID id) {
        bonsaiRepository.delete(id);
    }

    public Optional<Watering> getLatestWatering(UUID id) {
        Optional<Bonsai> bonsai = bonsaiRepository.findById(id);
        if (bonsai.isPresent()) {
            return bonsaiRepository.getLatestWatering(id);
        }
        return Optional.empty();
    }

    public Optional<Repotting> getLatestRepotting(UUID id) {
        Optional<Bonsai> bonsai = bonsaiRepository.findById(id);
        if (bonsai.isPresent()) {
            return bonsaiRepository.getLatestRepotting(id);
        }
        return Optional.empty();
    }

    public Optional<Pruning> getLatestPruning(UUID id) {
        Optional<Bonsai> bonsai = bonsaiRepository.findById(id);
        if (bonsai.isPresent()) {
            return bonsaiRepository.getLatestPruning(id);
        }
        return Optional.empty();
    }
}
