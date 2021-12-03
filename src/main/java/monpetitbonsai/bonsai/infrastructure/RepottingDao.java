package monpetitbonsai.bonsai.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepottingDao extends JpaRepository<RepottingEntity, UUID> {
}
