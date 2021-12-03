package monpetitbonsai.bonsai.exposition;

import java.util.Date;
import java.util.UUID;

public class RepottingDto {
    private UUID id;
    private Date repotting_date;

    public RepottingDto() {
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
}
