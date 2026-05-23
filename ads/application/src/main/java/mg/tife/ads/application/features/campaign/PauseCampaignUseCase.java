package mg.tife.ads.application.features.campaign;
import mg.tife.ads.domain.event.campaign.CampaignPauseEvent;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;

import java.util.UUID;
import java.util.logging.Logger;

public class PauseCampaignUseCase {
    private final CampaignRepository campaignRepository;
    private final EventPublisher eventPublisher;
    private final Logger logger = Logger.getLogger(PauseCampaignUseCase.class.getName());

    public PauseCampaignUseCase(
            CampaignRepository campRep,
            EventPublisher eventPublisher
    ) {
        this.campaignRepository = campRep;
        this.eventPublisher = eventPublisher;
    }

    public void execute(UUID campaignId) {
        logger.info("PauseCampaignUseCase: executing with campaignId: " + campaignId);
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow();
        campaign.pause();
        campaignRepository.save(campaign);
        eventPublisher.publishCampaignEvent(new CampaignPauseEvent(campaign));
        logger.info("PauseCampaignUseCase: campaign paused and event published for campaignId: " + campaignId);
    }
}