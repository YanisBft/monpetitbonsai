package monpetitbonsai.bonsai;

import monpetitbonsai.bonsai.domain.Owner;
import monpetitbonsai.commons.entity.OwnerEntity;

import java.util.UUID;

public class OwnerMapper {

    public static Owner toOwner(UUID owner_id) {
        Owner owner = new Owner();
        owner.setId(owner_id);
        return owner;
    }

    public static Owner toOwner(OwnerEntity ownerEntity) {
        Owner owner = new Owner();
        owner.setId(ownerEntity.getId());
        owner.setName(ownerEntity.getName());
        return owner;
    }

    public static OwnerEntity toOwnerEntity(Owner owner) {
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setId(owner.getId());
        ownerEntity.setName(owner.getName());
        return ownerEntity;
    }
}
