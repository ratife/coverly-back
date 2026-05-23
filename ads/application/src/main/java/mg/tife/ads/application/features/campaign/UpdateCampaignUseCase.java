package mg.tife.ads.application.features.campaign;
import mg.tife.ads.domain.event.campaign.CampaignUpdatedEvent;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;

import java.util.UUID;
import java.util.logging.Logger;

public class UpdateCampaignUseCase {

    private final CampaignRepository campaignRepository;
    private final EventPublisher eventPublisher;
    private final Logger logger = Logger.getLogger(UpdateCampaignUseCase.class.getName());

    public UpdateCampaignUseCase(
            CampaignRepository campRep,
            EventPublisher eventPublisher
    ) {
        this.campaignRepository = campRep;
        this.eventPublisher = eventPublisher;
    }

    public UUID execute(Campaign campaign) {
        logger.info("Executing CreateCampaignUseCase with campaign: " + campaign);
        Campaign saved = campaignRepository.save(campaign);
        eventPublisher.publishCampaignEvent(new CampaignUpdatedEvent(saved,campaign));
        return saved.getId();
    }
}