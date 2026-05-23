package mg.tife.ads.application.features.campaign;
import mg.tife.ads.application.dto.PaginateRequest;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;

import java.util.List;
import java.util.logging.Logger;

public class ListCampaignUseCase {

    private final CampaignRepository campaignRepository;
    private final Logger logger = Logger.getLogger(ListCampaignUseCase.class.getName());

    public ListCampaignUseCase(
            CampaignRepository campRep
    ) {
        this.campaignRepository = campRep;
    }

    public List<Campaign>  execute(PaginateRequest request) {
        List<Campaign> list = campaignRepository.find(request.page(), request.size());
        logger.info("list: " + list.size());
        return list;
    }
}