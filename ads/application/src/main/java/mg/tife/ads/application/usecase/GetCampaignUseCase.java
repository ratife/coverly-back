package mg.tife.ads.application.usecase;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;

import java.util.Optional;
import java.util.UUID;

public class GetCampaignUseCase {

    private final CampaignRepository campaignRepository;

    public GetCampaignUseCase(
            CampaignRepository campRep
    ) {
        this.campaignRepository = campRep;
    }

    public Optional<Campaign>  execute(UUID campaignId) {
        return campaignRepository.findById(campaignId);
    }
}