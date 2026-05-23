package mg.tife.ads.application.features.campaign;
import mg.tife.ads.domain.event.campaign.CampaignCreatedEvent;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;

import java.util.UUID;
import java.util.logging.Logger;

public class CreateCampaignUseCase {

    private final CampaignRepository campaignRepository;
    private final EventPublisher eventPublisher;
    private final Logger logger = Logger.getLogger(CreateCampaignUseCase.class.getName());

    public CreateCampaignUseCase(
            CampaignRepository campRep,
            EventPublisher eventPublisher
    ) {
        this.campaignRepository = campRep;
        this.eventPublisher = eventPublisher;
    }

    public UUID execute(Campaign campaign) {
        logger.info("Executing CreateCampaignUseCase with campaign: " + campaign);
        Campaign saved = campaignRepository.save(campaign);
        eventPublisher.publishCampaignEvent(new CampaignCreatedEvent(saved));
        logger.info("Campaign created with ID: " + saved.getId());
        return saved.getId();
    }
}