package monpetitbonsai.commons.dao;

import monpetitbonsai.commons.entity.PruningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PruningDao extends JpaRepository<PruningEntity, UUID> {
}
