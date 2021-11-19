package monpetitbonsai.bonsai.infrastructure;

import monpetitbonsai.BonsaiMapper;
import monpetitbonsai.bonsai.domain.Bonsai;
import monpetitbonsai.bonsai.domain.Pruning;
import monpetitbonsai.bonsai.domain.Repotting;
import monpetitbonsai.bonsai.domain.Watering;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BonsaiRepository {
    private BonsaiDao bonsaiDao;
    private WateringDao wateringDao;
    private RepottingDao repottingDao;
    private PruningDao pruningDao;

    public BonsaiRepository(BonsaiDao bonsaiDao, WateringDao wateringDao, RepottingDao repottingDao, PruningDao pruningDao) {
        this.bonsaiDao = bonsaiDao;
        this.wateringDao = wateringDao;
        this.repottingDao = repottingDao;
        this.pruningDao = pruningDao;
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

    public Optional<Watering> getLatestWatering(UUID id) {
        if (wateringDao.getWaterings(id).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(BonsaiMapper.toWatering(wateringDao.getWaterings(id).get(0)));
    }

    public Optional<Repotting> getLatestRepotting(UUID id) {
        if (repottingDao.getRepottings(id).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(BonsaiMapper.toRepotting(repottingDao.getRepottings(id).get(0)));
    }

    public Optional<Pruning> getLatestPruning(UUID id) {
        if (pruningDao.getPrunings(id).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(BonsaiMapper.toPruning(pruningDao.getPrunings(id).get(0)));
    }
}
