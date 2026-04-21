package mg.tife.ads.domain.model.campaign;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class Campaign {

    private UUID id;
    private UUID advertiserId;
    private String name;
    private CampaignObjective objective;
    private CampaignStatus status;
    private Budget budget;
    private CommissionPolicy commissionPolicy;
    private LocalDate startDate;
    private LocalDate endDate;

    private List<MissionTemplate> missionTemplates;

    public Campaign(UUID advertiserId) {
        this.status = CampaignStatus.DRAFT;
        this.advertiserId = advertiserId;
    }

    public void activate() {
        if (budget.remaining().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Cannot activate campaign with zero budget");
        }
        this.status = CampaignStatus.ACTIVE;
    }

    public void pause() {
        this.status = CampaignStatus.PAUSED;
    }

    public void consumeBudget(BigDecimal amount) {
        budget.consume(amount);
    }

    public void addMissionTemplate(MissionTemplate template) {

        if (status != CampaignStatus.DRAFT) {
            throw new IllegalStateException("Cannot modify campaign");
        }

        if (budget.remaining().compareTo(template.totalCost()) < 0) {
            throw new IllegalStateException("Insufficient budget");
        }

        missionTemplates.add(template);
    }

    public boolean isReady() {
        return status == CampaignStatus.READY;
    }

    public void markReady() {
        if (missionTemplates.isEmpty()) {
            throw new IllegalStateException("Campaign must contain missions");
        }
        status = CampaignStatus.READY;
    }
}