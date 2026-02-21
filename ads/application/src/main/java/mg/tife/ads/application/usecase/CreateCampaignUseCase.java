package mg.tife.ads.application.usecase;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;

import java.util.UUID;

public class CreateCampaignUseCase {

    private final CampaignRepository campaignRepository;
    private final EventPublisher eventPublisher;

    public CreateCampaignUseCase(
            CampaignRepository campRep,
            EventPublisher eventPublisher
    ) {
        this.campaignRepository = campRep;
        this.eventPublisher = eventPublisher;
    }

    public UUID execute(Campaign campaign) {
        Campaign saved = campaignRepository.save(campaign);
        eventPublisher.publishCampaignCreated(saved);
        return saved.getId();
    }
}