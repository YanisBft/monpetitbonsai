package monpetitbonsai.bonsai.domain;

import java.util.UUID;

public class Owner {
    private UUID id;
    private String name;

    public Owner() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
