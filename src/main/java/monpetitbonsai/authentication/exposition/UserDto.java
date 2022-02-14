package monpetitbonsai.authentication.exposition;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDto {
    private UUID id;
    private String username;
    private String password;
    private boolean enabled;
    private List<AuthorityDto> authorities;

    public UserDto() {
        this.authorities = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<AuthorityDto> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityDto> authorities) {
        this.authorities = authorities;
    }
}
