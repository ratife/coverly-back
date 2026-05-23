package mg.tife.ads.application.features.campaign;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class GetCampaignUseCase {

    private final CampaignRepository campaignRepository;
    private final Logger logger = Logger.getLogger(GetCampaignUseCase.class.getName());

    public GetCampaignUseCase(
            CampaignRepository campRep
    ) {
        this.campaignRepository = campRep;
    }

    public Optional<Campaign>  execute(UUID campaignId) {
        logger.info("GetCampaignUseCase: executing with campaignId: " + campaignId);
        return campaignRepository.findById(campaignId);
    }
}