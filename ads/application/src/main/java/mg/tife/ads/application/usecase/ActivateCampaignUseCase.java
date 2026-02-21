package mg.tife.ads.application.usecase;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;

import java.util.UUID;

public class ActivateCampaignUseCase {

    private final CampaignRepository campaignRepository;
    private final EventPublisher eventPublisher;

    public ActivateCampaignUseCase(
            CampaignRepository campRep,
            EventPublisher eventPublisher
    ) {
        this.campaignRepository = campRep;
        this.eventPublisher = eventPublisher;
    }

    public UUID execute(UUID campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow();
        campaign.activate();
        campaign = campaignRepository.save(campaign);
        eventPublisher.publishCampaignActivated(campaign);
        return campaign.getId();
    }
}