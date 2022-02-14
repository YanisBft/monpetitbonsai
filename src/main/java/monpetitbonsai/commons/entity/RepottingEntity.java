package monpetitbonsai.commons.entity;

import monpetitbonsai.commons.entity.BonsaiEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "repotting")
@Table(name = "repotting")
public class RepottingEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name = "repotting_date")
    private Date repotting_date;
    @ManyToOne(targetEntity = BonsaiEntity.class, cascade = CascadeType.ALL) @JoinColumn(name = "bonsai_id")
    private BonsaiEntity bonsai;

    public RepottingEntity() {
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

    public BonsaiEntity getBonsai() {
        return bonsai;
    }

    public void setBonsai(BonsaiEntity bonsai) {
        this.bonsai = bonsai;
    }
}
