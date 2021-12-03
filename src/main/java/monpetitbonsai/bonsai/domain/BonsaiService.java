package monpetitbonsai.bonsai.domain;

import monpetitbonsai.bonsai.infrastructure.BonsaiRepository;
import monpetitbonsai.commons.Status;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BonsaiService {
    private BonsaiRepository bonsaiRepository;

    public BonsaiService(BonsaiRepository bonsaiRepository) {
        this.bonsaiRepository = bonsaiRepository;
    }

    public List<Bonsai> findAll(Status status, int older_than, Sort sort) {
        return bonsaiRepository.findAll(status, older_than, sort);
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

    public Optional<Bonsai> updateStatus(UUID id, Status status) {
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
        if (bonsai.isPresent() && !bonsaiRepository.getWaterings(id).isEmpty()) {
            return Optional.of(bonsaiRepository.getWaterings(id).get(0));
        }
        return Optional.empty();
    }

    public List<Watering> getWaterings(UUID id) {
        return bonsaiRepository.getWaterings(id);
    }

    public Optional<Repotting> getLatestRepotting(UUID id) {
        Optional<Bonsai> bonsai = bonsaiRepository.findById(id);
        if (bonsai.isPresent() && !bonsaiRepository.getRepottings(id).isEmpty()) {
            return Optional.of(bonsaiRepository.getRepottings(id).get(0));
        }
        return Optional.empty();
    }

    public List<Repotting> getRepottings(UUID id) {
        return bonsaiRepository.getRepottings(id);
    }

    public Optional<Pruning> getLatestPruning(UUID id) {
        Optional<Bonsai> bonsai = bonsaiRepository.findById(id);
        if (bonsai.isPresent() && !bonsaiRepository.getPrunings(id).isEmpty()) {
            return Optional.of(bonsaiRepository.getPrunings(id).get(0));
        }
        return Optional.empty();
    }

    public List<Pruning> getPrunings(UUID id) {
        return bonsaiRepository.getPrunings(id);
    }
}
