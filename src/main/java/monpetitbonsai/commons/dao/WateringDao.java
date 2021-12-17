package monpetitbonsai.commons.dao;

import monpetitbonsai.commons.entity.WateringEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WateringDao extends JpaRepository<WateringEntity, UUID> {
}
