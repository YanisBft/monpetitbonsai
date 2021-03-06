package monpetitbonsai.authentication.infrastructure;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "authorities")
@Entity
public class AuthorityEntity {

    @EmbeddedId
    private AuthorityId authorityId;

//    @ManyToOne(targetEntity = UserEntity.class)
//    private UserEntity user;

    public AuthorityEntity() {
    }

    public AuthorityEntity(AuthorityId authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthority() {
        return authorityId.getAuthority();
    }

    public AuthorityId getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(AuthorityId authorityId) {
        this.authorityId = authorityId;
    }
}
