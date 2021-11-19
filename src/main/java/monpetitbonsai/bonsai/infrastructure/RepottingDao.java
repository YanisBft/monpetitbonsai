package monpetitbonsai.bonsai.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepottingDao extends JpaRepository<RepottingEntity, UUID> {

    @Query(value = "SELECT r FROM repotting r WHERE bonsai_id = :id ORDER BY repotting_date DESC")
    List<RepottingEntity> getRepottings(@Param("id") UUID id);
}
