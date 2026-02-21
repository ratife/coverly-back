package mg.tife.ads.application.usecase;

import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;

import java.math.BigDecimal;
import java.util.UUID;

public class ConsumeBudgetUseCase {


    private final CampaignRepository campaignRepository;
    private final EventPublisher eventPublisher;

    public ConsumeBudgetUseCase(
            CampaignRepository campRep,
            EventPublisher eventPublisher
    ) {
        this.campaignRepository = campRep;
        this.eventPublisher = eventPublisher;
    }


    public void execute(UUID campaignId, BigDecimal amount) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow();

        campaign.consumeBudget(amount);

        campaignRepository.save(campaign);
    }
}