package mg.tife.ads.application.usecase;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;

import java.util.UUID;

public class PauseCampaignUseCase {
    private final CampaignRepository campaignRepository;
    private final EventPublisher eventPublisher;

    public PauseCampaignUseCase(
            CampaignRepository campRep,
            EventPublisher eventPublisher
    ) {
        this.campaignRepository = campRep;
        this.eventPublisher = eventPublisher;
    }

    public void execute(UUID campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow();
        campaign.pause();
        campaignRepository.save(campaign);
    }
}