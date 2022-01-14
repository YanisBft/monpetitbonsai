package monpetitbonsai.authentication.exposition;

import java.util.UUID;

public class AuthorityDto {
    private UUID id;
    private String authority;

    public AuthorityDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
