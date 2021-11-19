package monpetitbonsai.bonsai.infrastructure;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "pruning")
@Table(name = "pruning")
public class PruningEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name = "pruning_date")
    private Date pruning_date;
    @Column(name = "bonsai_id")
    private UUID bonsai_id;

    public PruningEntity() {
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
