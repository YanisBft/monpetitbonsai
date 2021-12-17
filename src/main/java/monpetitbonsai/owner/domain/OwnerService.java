package monpetitbonsai.owner.domain;

import monpetitbonsai.owner.infrastructure.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OwnerService {
    private OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> findAll(int has_more) {
        return ownerRepository.findAll(has_more);
    }

    public Optional<Owner> findById(UUID id) {
        return ownerRepository.findById(id);
    }

    public Owner create(Owner owner) {
        return ownerRepository.create(owner);
    }

    public List<Bonsai> getBonsais(UUID id) {
        return ownerRepository.getBonsais(id);
    }

    public Optional<Bonsai> transferBonsai(UUID owner_id, UUID bonsai_id, Owner new_owner) {
        Optional<Owner> owner = ownerRepository.findById(owner_id);
        Optional<Bonsai> bonsai = ownerRepository.findBonsaiById(bonsai_id);
        if (owner.isPresent() && bonsai.isPresent()) {
            for (Bonsai b : owner.get().getBonsais()) {
                if (b.getId().equals(bonsai_id)) {
                    return Optional.of(ownerRepository.transferBonsai(new_owner, bonsai.get()));
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Bonsai> addBonsai(UUID owner_id, Bonsai bonsai) {
        Optional<Owner> owner = ownerRepository.findById(owner_id);
        if (owner.isPresent()) {
            for (Owner o : findAll(-1)) {
                for (Bonsai b : o.getBonsais()) {
                    if (b.getId().equals(bonsai.getId())) {
                        return Optional.empty();
                    }
                }
            }
            return Optional.of(ownerRepository.addBonsai(owner.get(), bonsai));
        }
        return Optional.empty();
    }
}
