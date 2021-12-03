package monpetitbonsai.bonsai.infrastructure;

import monpetitbonsai.commons.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BonsaiDao extends JpaRepository<BonsaiEntity, UUID> {

    @Query("SELECT b FROM bonsai b WHERE status = :status AND acquisition_age > :older_than")
    List<BonsaiEntity> findAllFilteredByStatus(@Param("status") Status status, @Param("older_than") int older_than, Sort sort);

    @Query("SELECT b FROM bonsai b WHERE acquisition_age > :older_than")
    List<BonsaiEntity> findAllFiltered(@Param("older_than") int older_than, Sort sort);
}
