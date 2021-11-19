package monpetitbonsai;

import monpetitbonsai.bonsai.domain.Bonsai;
import monpetitbonsai.bonsai.domain.Pruning;
import monpetitbonsai.bonsai.domain.Repotting;
import monpetitbonsai.bonsai.domain.Watering;
import monpetitbonsai.bonsai.exposition.BonsaiDto;
import monpetitbonsai.bonsai.infrastructure.BonsaiEntity;
import monpetitbonsai.bonsai.infrastructure.PruningEntity;
import monpetitbonsai.bonsai.infrastructure.RepottingEntity;
import monpetitbonsai.bonsai.infrastructure.WateringEntity;

public class BonsaiMapper {

    public static Bonsai toBonsai(BonsaiEntity bonsaiEntity) {
        Bonsai bonsai = new Bonsai();
        bonsai.setId(bonsaiEntity.getId());
        bonsai.setName(bonsaiEntity.getName());
        bonsai.setSpecies(bonsaiEntity.getSpecies());
        bonsai.setAcquisition_date(bonsaiEntity.getAcquisition_date());
        bonsai.setAcquisition_age(bonsaiEntity.getAcquisition_age());
        bonsai.setStatus(bonsaiEntity.getStatus());
        return bonsai;
    }

    public static Bonsai toBonsai(BonsaiDto bonsaiDto) {
        Bonsai bonsai = new Bonsai();
        bonsai.setId(bonsaiDto.getId());
        bonsai.setName(bonsaiDto.getName());
        bonsai.setSpecies(bonsaiDto.getSpecies());
        bonsai.setAcquisition_date(bonsaiDto.getAcquisition_date());
        bonsai.setAcquisition_age(bonsaiDto.getAcquisition_age());
        bonsai.setStatus(bonsaiDto.getStatus());
        return bonsai;
    }

    public static BonsaiDto toBonsaiDto(Bonsai bonsai) {
        BonsaiDto bonsaiDto = new BonsaiDto();
        bonsaiDto.setId(bonsai.getId());
        bonsaiDto.setName(bonsai.getName());
        bonsaiDto.setSpecies(bonsai.getSpecies());
        bonsaiDto.setAcquisition_date(bonsai.getAcquisition_date());
        bonsaiDto.setAcquisition_age(bonsai.getAcquisition_age());
        bonsaiDto.setStatus(bonsai.getStatus());
        return bonsaiDto;
    }

    public static BonsaiEntity toBonsaiEntity(Bonsai bonsai) {
        BonsaiEntity bonsaiEntity = new BonsaiEntity();
        bonsaiEntity.setId(bonsai.getId());
        bonsaiEntity.setName(bonsai.getName());
        bonsaiEntity.setSpecies(bonsai.getSpecies());
        bonsaiEntity.setAcquisition_date(bonsai.getAcquisition_date());
        bonsaiEntity.setAcquisition_age(bonsai.getAcquisition_age());
        bonsaiEntity.setStatus(bonsai.getStatus());
        return bonsaiEntity;
    }

    public static Watering toWatering(WateringEntity wateringEntity) {
        Watering watering = new Watering();
        watering.setId(wateringEntity.getId());
        watering.setWatering_date(wateringEntity.getWatering_date());
        watering.setBonsai_id(wateringEntity.getBonsai_id());
        return watering;
    }

    public static Repotting toRepotting(RepottingEntity repottingEntity) {
        Repotting repotting = new Repotting();
        repotting.setId(repottingEntity.getId());
        repotting.setRepotting_date(repottingEntity.getRepotting_date());
        repotting.setBonsai_id(repottingEntity.getBonsai_id());
        return repotting;
    }


    public static Pruning toPruning(PruningEntity pruningEntity) {
        Pruning pruning = new Pruning();
        pruning.setId(pruningEntity.getId());
        pruning.setPruning_date(pruningEntity.getPruning_date());
        pruning.setBonsai_id(pruningEntity.getBonsai_id());
        return pruning;
    }
}
