package monpetitbonsai.commons.dao;

import monpetitbonsai.commons.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface OwnerDao extends JpaRepository<OwnerEntity, UUID> {

    @Query("SELECT o FROM owner o, bonsai b WHERE (:has_more = -1 OR o.id = b.owner) GROUP BY o HAVING COUNT(b) > :has_more")
    List<OwnerEntity> findAllFiltered(@Param("has_more") int has_more);

    @Transactional
    @Modifying
    @Query("UPDATE bonsai SET owner_id = :owner_id WHERE id = :bonsai_id")
    void updateOwnerId(@Param("owner_id") UUID owner_id, @Param("bonsai_id") UUID bonsai_id);
}
