package monpetitbonsai.bonsai.domain;

import java.util.Date;
import java.util.UUID;

public class Repotting {
    private UUID id;
    private Date repotting_date;
    private UUID bonsai_id;

    public Repotting() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getRepotting_date() {
        return repotting_date;
    }

    public void setRepotting_date(Date repotting_date) {
        this.repotting_date = repotting_date;
    }

    public UUID getBonsai_id() {
        return bonsai_id;
    }

    public void setBonsai_id(UUID bonsai_id) {
        this.bonsai_id = bonsai_id;
    }
}
