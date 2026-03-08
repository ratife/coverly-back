package mg.tife.ads.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import mg.tife.ads.domain.model.campaign.CampaignObjective;
import mg.tife.ads.domain.model.campaign.CampaignStatus;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "campaigns")
public class CampaignEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private UUID advertiserId;

    @Column(nullable = false)
    private String name;

    private CampaignObjective objective;

    private CampaignStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "budget_id")
    private BudgetEntity budget;

    @Column(nullable = false)
    private boolean active;

    private LocalDate startDate;

    private LocalDate endDate;
}