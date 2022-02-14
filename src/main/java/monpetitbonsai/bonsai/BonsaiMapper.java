package monpetitbonsai.bonsai;

import monpetitbonsai.bonsai.domain.Bonsai;
import monpetitbonsai.bonsai.domain.Pruning;
import monpetitbonsai.bonsai.domain.Repotting;
import monpetitbonsai.bonsai.domain.Watering;
import monpetitbonsai.bonsai.exposition.BonsaiDto;
import monpetitbonsai.bonsai.exposition.PruningDto;
import monpetitbonsai.bonsai.exposition.RepottingDto;
import monpetitbonsai.bonsai.exposition.WateringDto;
import monpetitbonsai.commons.entity.BonsaiEntity;
import monpetitbonsai.commons.entity.PruningEntity;
import monpetitbonsai.commons.entity.RepottingEntity;
import monpetitbonsai.commons.entity.WateringEntity;

public class BonsaiMapper {

    public static Bonsai toBonsai(BonsaiEntity bonsaiEntity) {
        Bonsai bonsai = new Bonsai();
        bonsai.setId(bonsaiEntity.getId());
        bonsai.setName(bonsaiEntity.getName());
        bonsai.setSpecies(bonsaiEntity.getSpecies());
        bonsai.setAcquisition_date(bonsaiEntity.getAcquisition_date());
        bonsai.setAcquisition_age(bonsaiEntity.getAcquisition_age());
        bonsai.setStatus(bonsaiEntity.getStatus());
        bonsai.setOwner(OwnerMapper.toOwner(bonsaiEntity.getOwner()));
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
        bonsai.setOwner(OwnerMapper.toOwner(bonsaiDto.getOwner_id()));
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
        bonsaiDto.setOwner_id(bonsai.getOwner().getId());
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
        bonsaiEntity.setOwner(OwnerMapper.toOwnerEntity(bonsai.getOwner()));
        return bonsaiEntity;
    }

    public static Watering toWatering(WateringEntity wateringEntity) {
        Watering watering = new Watering();
        watering.setId(wateringEntity.getId());
        watering.setWatering_date(wateringEntity.getWatering_date());
        watering.setBonsai(BonsaiMapper.toBonsai(wateringEntity.getBonsai()));
        return watering;
    }

    public static WateringDto toWateringDto(Watering watering) {
        WateringDto wateringDto = new WateringDto();
        wateringDto.setId(watering.getId());
        wateringDto.setWatering_date(watering.getWatering_date());
        return wateringDto;
    }

    public static Repotting toRepotting(RepottingEntity repottingEntity) {
        Repotting repotting = new Repotting();
        repotting.setId(repottingEntity.getId());
        repotting.setRepotting_date(repottingEntity.getRepotting_date());
        repotting.setBonsai(BonsaiMapper.toBonsai(repottingEntity.getBonsai()));
        return repotting;
    }

    public static RepottingDto toRepottingDto(Repotting repotting) {
        RepottingDto repottingDto = new RepottingDto();
        repottingDto.setId(repotting.getId());
        repottingDto.setRepotting_date(repotting.getRepotting_date());
        return repottingDto;
    }

    public static Pruning toPruning(PruningEntity pruningEntity) {
        Pruning pruning = new Pruning();
        pruning.setId(pruningEntity.getId());
        pruning.setPruning_date(pruningEntity.getPruning_date());
        pruning.setBonsai(BonsaiMapper.toBonsai(pruningEntity.getBonsai()));
        return pruning;
    }

    public static PruningDto toPruningDto(Pruning pruning) {
        PruningDto pruningDto = new PruningDto();
        pruningDto.setId(pruning.getId());
        pruningDto.setPruning_date(pruning.getPruning_date());
        return pruningDto;
    }
}
