package monpetitbonsai.bonsai.infrastructure;

import monpetitbonsai.bonsai.BonsaiMapper;
import monpetitbonsai.bonsai.domain.Bonsai;
import monpetitbonsai.bonsai.domain.Pruning;
import monpetitbonsai.bonsai.domain.Repotting;
import monpetitbonsai.bonsai.domain.Watering;
import monpetitbonsai.commons.Status;
import monpetitbonsai.commons.dao.BonsaiDao;
import monpetitbonsai.commons.dao.PruningDao;
import monpetitbonsai.commons.dao.RepottingDao;
import monpetitbonsai.commons.dao.WateringDao;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Comparator;
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

    public List<Bonsai> findAll(Status status, int older_than, Sort sort) {
        if (status != null) {
            return bonsaiDao.findAllFilteredByStatus(status, older_than, sort).stream().map(BonsaiMapper::toBonsai).collect(Collectors.toList());
        }
        return bonsaiDao.findAllFiltered(older_than, sort).stream().map(BonsaiMapper::toBonsai).collect(Collectors.toList());
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

    public List<Watering> getWaterings(UUID id) {
        return wateringDao.findAll().stream()
                .filter(w -> w.getBonsai().getId().equals(id))
                .map(BonsaiMapper::toWatering)
                .sorted(Comparator.comparing(Watering::getWatering_date).reversed())
                .collect(Collectors.toList());
    }

    public List<Repotting> getRepottings(UUID id) {
        return repottingDao.findAll().stream()
                .filter(r -> r.getBonsai().getId().equals(id))
                .map(BonsaiMapper::toRepotting)
                .sorted(Comparator.comparing(Repotting::getRepotting_date).reversed())
                .collect(Collectors.toList());
    }

    public List<Pruning> getPrunings(UUID id) {
        return pruningDao.findAll().stream()
                .filter(p -> p.getBonsai().getId().equals(id))
                .map(BonsaiMapper::toPruning)
                .sorted(Comparator.comparing(Pruning::getPruning_date).reversed())
                .collect(Collectors.toList());
    }
}
