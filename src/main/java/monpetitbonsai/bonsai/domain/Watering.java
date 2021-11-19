package monpetitbonsai.bonsai.domain;

import java.util.Date;
import java.util.UUID;

public class Watering {
    private UUID id;
    private Date watering_date;
    private UUID bonsai_id;

    public Watering() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getWatering_date() {
        return watering_date;
    }

    public void setWatering_date(Date watering_date) {
        this.watering_date = watering_date;
    }

    public UUID getBonsai_id() {
        return bonsai_id;
    }

    public void setBonsai_id(UUID bonsai_id) {
        this.bonsai_id = bonsai_id;
    }
}
