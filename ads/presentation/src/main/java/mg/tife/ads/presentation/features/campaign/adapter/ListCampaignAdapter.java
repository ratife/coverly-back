package mg.tife.ads.presentation.features.campaign.adapter;

import mg.tife.ads.application.dto.PaginateRequest;
import mg.tife.ads.application.features.campaign.ListCampaignUseCase;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.presentation.features.campaign.dto.mapper.CampaignDtoMapper;
import mg.tife.ads.presentation.features.campaign.dto.response.CampaignResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCampaignAdapter {

    private final ListCampaignUseCase delegate;

     public ListCampaignAdapter(CampaignRepository campaignRepository) {
         delegate = new ListCampaignUseCase(campaignRepository);
     }

     public List<CampaignResponse> list(PaginateRequest request) {
         List<Campaign> list =  delegate.execute(request);
         System.out.println("list: " + list.size());
         return list.stream().map(CampaignDtoMapper.INSTANCE::toResponse).toList();
     }
}