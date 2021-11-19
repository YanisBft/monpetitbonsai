package monpetitbonsai.bonsai.infrastructure;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "watering")
@Table(name = "watering")
public class WateringEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name = "watering_date")
    private Date watering_date;
    @Column(name = "bonsai_id")
    private UUID bonsai_id;

    public WateringEntity() {
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
