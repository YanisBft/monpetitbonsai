package monpetitbonsai.bonsai.exposition;

import java.util.Date;
import java.util.UUID;

public class PruningDto {
    private UUID id;
    private Date pruning_date;

    public PruningDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getPruning_date() {
        return pruning_date;
    }

    public void setPruning_date(Date pruning_date) {
        this.pruning_date = pruning_date;
    }
}
