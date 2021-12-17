package monpetitbonsai.commons.dao;

import monpetitbonsai.commons.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface OwnerDao extends JpaRepository<OwnerEntity, UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE bonsai SET owner_id = :owner_id WHERE id = :bonsai_id")
    void updateOwnerId(@Param("owner_id") UUID owner_id, @Param("bonsai_id") UUID bonsai_id);
}
