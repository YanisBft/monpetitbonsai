package monpetitbonsai.bonsai.domain;

import java.util.Date;
import java.util.UUID;

public class Pruning {
    private UUID id;
    private Date pruning_date;
    private UUID bonsai_id;

    public Pruning() {
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

    public UUID getBonsai_id() {
        return bonsai_id;
    }

    public void setBonsai_id(UUID bonsai_id) {
        this.bonsai_id = bonsai_id;
    }
}
