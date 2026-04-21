package mg.tife.ads.application.usecase;
import mg.tife.ads.application.dto.PaginateRequest;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;

import java.util.List;

public class ListCampaignUseCase {

    private final CampaignRepository campaignRepository;

    public ListCampaignUseCase(
            CampaignRepository campRep
    ) {
        this.campaignRepository = campRep;
    }

    public List<Campaign>  execute(PaginateRequest request) {
        List<Campaign> list = campaignRepository.find(request.page(), request.size());
        System.out.println("list: " + list.size());
        return list;
    }
}