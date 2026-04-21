package mg.tife.ads.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "missions")
public class MissionEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private UUID campaignId;

    @Column(nullable = false)
    private UUID publisherId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MissionTypeEntity type;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MissionStatusEntity status;

    @Column
    private String proofUrl;

    @Column
    private LocalDateTime proofSubmittedAt;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column
    private LocalDateTime validatedAt;

    public enum MissionTypeEntity {
        POST,
        STORY,
        COVER,
        VIDEO,
        REEL
    }

    public enum MissionStatusEntity {
        CREATED,
        ACCEPTED,
        IN_PROGRESS,
        SUBMITTED,
        VALIDATED,
        REJECTED,
        PAID,
        CANCELLED
    }
}