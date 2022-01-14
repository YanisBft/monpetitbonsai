package monpetitbonsai.owner.infrastructure;

import monpetitbonsai.owner.OwnerMapper;
import monpetitbonsai.commons.dao.BonsaiDao;
import monpetitbonsai.commons.dao.OwnerDao;
import monpetitbonsai.owner.domain.Bonsai;
import monpetitbonsai.owner.domain.Owner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OwnerRepository {
    private OwnerDao ownerDao;
    private BonsaiDao bonsaiDao;

    public OwnerRepository(OwnerDao ownerDao, BonsaiDao bonsaiDao) {
        this.ownerDao = ownerDao;
        this.bonsaiDao = bonsaiDao;
    }

    public List<Owner> findAll(int has_more) {
        return ownerDao.findAllFiltered(has_more).stream().map(OwnerMapper::toOwner).collect(Collectors.toList());
    }

    public Optional<Owner> findById(UUID id) {
        return ownerDao.findById(id).map(OwnerMapper::toOwner);
    }

    public Owner create(Owner owner) {
        return OwnerMapper.toOwner(ownerDao.save(OwnerMapper.toOwnerEntity(owner)));
    }

    public Owner update(Owner owner) {
        return OwnerMapper.toOwner(ownerDao.save(OwnerMapper.toOwnerEntity(owner)));
    }

    public void delete(UUID id) {
        ownerDao.deleteById(id);
    }

    public List<Bonsai> getBonsais(UUID id) {
        return bonsaiDao.findAll().stream()
                .filter(b -> b.getOwner() != null && b.getOwner().getId().equals(id))
                .map(OwnerMapper::toBonsai)
                .collect(Collectors.toList());
    }

    public Bonsai transferBonsai(Owner newOwner, Bonsai bonsai) {
        ownerDao.updateOwnerId(newOwner.getId(), bonsai.getId());
        return bonsai;
    }

    public Bonsai addBonsai(Owner owner, Bonsai bonsai) {
        ownerDao.updateOwnerId(owner.getId(), bonsai.getId());
        return bonsai;
    }

    public Optional<Bonsai> findBonsaiById(UUID id) {
        return bonsaiDao.findById(id).map(OwnerMapper::toBonsai);
    }
}
