package monpetitbonsai.bonsai.exposition;

import java.util.Date;
import java.util.UUID;

public class WateringDto {
    private UUID id;
    private Date watering_date;

    public WateringDto() {
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
}
