package mg.tife.ads.application.features.campaign;

import mg.tife.ads.domain.event.campaign.BudgetConsumedEvent;
import mg.tife.ads.domain.event.campaign.CampaignBudgetExceededEvent;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.logging.Logger;

public class ConsumeBudgetUseCase {

    private final CampaignRepository campaignRepository;
    private final EventPublisher eventPublisher;
    private final Logger logger = Logger.getLogger(ConsumeBudgetUseCase.class.getName());

    public ConsumeBudgetUseCase(
            CampaignRepository campRep,
            EventPublisher eventPublisher
    ) {
        this.campaignRepository = campRep;
        this.eventPublisher = eventPublisher;
    }

    public void execute(UUID campaignId, BigDecimal amount) {
        logger.info("ConsumeBudgetUseCase: executing with campaignId: " + campaignId + " and amount: " + amount);
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow();
        campaign.consumeBudget(amount);
        campaignRepository.save(campaign);
        logger.info("ConsumeBudgetUseCase: budget consumed, campaign updated: " + campaign);
        eventPublisher.publishCampaignEvent(new BudgetConsumedEvent(campaign, amount));
        if(campaign.getBudget().isEmptyBudget()){
            eventPublisher.publishCampaignEvent(new CampaignBudgetExceededEvent(campaign));
        }
    }
}