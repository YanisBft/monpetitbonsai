package monpetitbonsai.bonsai.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WateringDao extends JpaRepository<WateringEntity, UUID> {

    @Query(value = "SELECT w FROM watering w WHERE bonsai_id = :id ORDER BY watering_date DESC")
    List<WateringEntity> getWaterings(@Param("id") UUID id);
}
