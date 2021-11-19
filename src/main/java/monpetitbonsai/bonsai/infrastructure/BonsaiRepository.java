package monpetitbonsai.bonsai.infrastructure;

import monpetitbonsai.BonsaiMapper;
import monpetitbonsai.bonsai.domain.Bonsai;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BonsaiRepository {
    private BonsaiDao bonsaiDao;

    public BonsaiRepository(BonsaiDao bonsaiDao) {
        this.bonsaiDao = bonsaiDao;
    }

    public List<Bonsai> findAll() {
        return bonsaiDao.findAll().stream().map(BonsaiMapper::toBonsai).collect(Collectors.toList());
    }

    public Optional<Bonsai> findById(UUID id) {
        return bonsaiDao.findById(id).map(BonsaiMapper::toBonsai);
    }

    public Bonsai create(Bonsai bonsai) {
        return BonsaiMapper.toBonsai(bonsaiDao.save(BonsaiMapper.toBonsaiEntity(bonsai)));
    }

    public Bonsai update(Bonsai bonsai) {
        return BonsaiMapper.toBonsai(bonsaiDao.save(BonsaiMapper.toBonsaiEntity(bonsai)));
    }

    public Bonsai updateStatus(Bonsai bonsai) {
        return BonsaiMapper.toBonsai(bonsaiDao.save(BonsaiMapper.toBonsaiEntity(bonsai)));
    }

    public void delete(UUID id) {
        bonsaiDao.deleteById(id);
    }
}
