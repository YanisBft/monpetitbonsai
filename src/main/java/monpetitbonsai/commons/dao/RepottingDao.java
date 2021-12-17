package monpetitbonsai.commons.dao;

import monpetitbonsai.commons.entity.RepottingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepottingDao extends JpaRepository<RepottingEntity, UUID> {
}
