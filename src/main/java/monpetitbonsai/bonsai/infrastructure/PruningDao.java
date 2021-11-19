package monpetitbonsai.bonsai.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PruningDao extends JpaRepository<PruningEntity, UUID> {

    @Query(value = "SELECT p FROM pruning p WHERE bonsai_id = :id ORDER BY pruning_date DESC")
    List<PruningEntity> getPrunings(@Param("id") UUID id);
}
