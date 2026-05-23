package mg.tife.ads.application.features.campaign;
import mg.tife.ads.domain.event.campaign.CampaignActivatedEvent;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;

import java.util.UUID;
import java.util.logging.Logger;

public class ActivateCampaignUseCase {

    private final CampaignRepository campaignRepository;
    private final EventPublisher eventPublisher;

    private final Logger logger = Logger.getLogger(ActivateCampaignUseCase.class.getName());

    public ActivateCampaignUseCase(
            CampaignRepository campRep,
            EventPublisher eventPublisher
    ) {
        this.campaignRepository = campRep;
        this.eventPublisher = eventPublisher;
    }

    public UUID execute(UUID campaignId) {
        logger.info("Activating campaign with ID: " + campaignId);
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow();
        campaign.activate();
        campaign = campaignRepository.save(campaign);
        eventPublisher.publishCampaignEvent(new CampaignActivatedEvent(campaign));
        logger.info("Campaign activated successfully: " + campaign);
        return campaign.getId();
    }
}