package monpetitbonsai.authentication.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<UserEntity, UUID> {

    UserEntity findByUsername(String username) throws UsernameNotFoundException;

    @Query(value = "SELECT A.authority FROM authorities A WHERE A.id = ?1", nativeQuery = true)
    List<String> findAuthorityByUserId(UUID id);
}
