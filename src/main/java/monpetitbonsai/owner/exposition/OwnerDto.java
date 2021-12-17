package monpetitbonsai.owner.exposition;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OwnerDto {
    private UUID id;
    private String name;
    private List<BonsaiDto> bonsais;

    public OwnerDto() {
        this.bonsais = new ArrayList<>();
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

    public List<BonsaiDto> getBonsais() {
        return bonsais;
    }

    public void setBonsais(List<BonsaiDto> bonsais) {
        this.bonsais = bonsais;
    }
}
