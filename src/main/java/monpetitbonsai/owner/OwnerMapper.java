package monpetitbonsai.owner;

import monpetitbonsai.commons.entity.BonsaiEntity;
import monpetitbonsai.owner.domain.Bonsai;
import monpetitbonsai.owner.domain.Owner;
import monpetitbonsai.owner.exposition.BonsaiDto;
import monpetitbonsai.owner.exposition.OwnerDto;
import monpetitbonsai.commons.entity.OwnerEntity;

import java.util.stream.Collectors;

public class OwnerMapper {

    public static Owner toOwner(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setId(ownerDto.getId());
        owner.setName(ownerDto.getName());
        owner.setBonsais(ownerDto.getBonsais().stream().map(BonsaiMapper::toBonsai).collect(Collectors.toList()));
        return owner;
    }

    public static Owner toOwner(OwnerEntity ownerEntity) {
        Owner owner = new Owner();
        owner.setId(ownerEntity.getId());
        owner.setName(ownerEntity.getName());
        owner.setBonsais(ownerEntity.getBonsais().stream().map(BonsaiMapper::toBonsai).collect(Collectors.toList()));
        return owner;
    }

    public static OwnerDto toOwnerDto(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(owner.getId());
        ownerDto.setName(owner.getName());
        ownerDto.setBonsais(owner.getBonsais().stream().map(BonsaiMapper::toBonsaiDto).collect(Collectors.toList()));
        return ownerDto;
    }

    public static OwnerEntity toOwnerEntity(Owner owner) {
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setId(owner.getId());
        ownerEntity.setName(owner.getName());
        ownerEntity.setBonsais(owner.getBonsais().stream().map(BonsaiMapper::toBonsaiEntity).collect(Collectors.toList()));
        return ownerEntity;
    }

    public static Bonsai toBonsai(BonsaiEntity bonsaiEntity) {
        Bonsai bonsai = new Bonsai();
        bonsai.setId(bonsaiEntity.getId());
        bonsai.setName(bonsaiEntity.getName());
        bonsai.setSpecies(bonsaiEntity.getSpecies());
        bonsai.setAcquisition_age(bonsaiEntity.getAcquisition_age());
        return bonsai;
    }

    public static Bonsai toBonsai(BonsaiDto bonsaiDto) {
        Bonsai bonsai = new Bonsai();
        bonsai.setId(bonsaiDto.getId());
        bonsai.setName(bonsaiDto.getName());
        bonsai.setSpecies(bonsaiDto.getSpecies());
        bonsai.setAcquisition_age(bonsaiDto.getAcquisition_age());
        return bonsai;
    }

    public static BonsaiDto toBonsaiDto(Bonsai bonsai) {
        BonsaiDto bonsaiDto = new BonsaiDto();
        bonsaiDto.setId(bonsai.getId());
        bonsaiDto.setName(bonsai.getName());
        bonsaiDto.setSpecies(bonsai.getSpecies());
        bonsaiDto.setAcquisition_age(bonsai.getAcquisition_age());
        return bonsaiDto;
    }

    public static BonsaiEntity toBonsaiEntity(Bonsai bonsai) {
        BonsaiEntity bonsaiEntity = new BonsaiEntity();
        bonsaiEntity.setId(bonsai.getId());
        bonsaiEntity.setName(bonsai.getName());
        bonsaiEntity.setSpecies(bonsai.getSpecies());
        bonsaiEntity.setAcquisition_age(bonsai.getAcquisition_age());
        return bonsaiEntity;
    }
}
