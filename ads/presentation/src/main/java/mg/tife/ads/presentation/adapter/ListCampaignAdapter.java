package mg.tife.ads.presentation.adapter;

import mg.tife.ads.application.dto.PaginateRequest;
import mg.tife.ads.application.usecase.ListCampaignUseCase;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.presentation.dto.campaign.mapper.CampaignDtoMapper;
import mg.tife.ads.presentation.dto.campaign.response.CampaignResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCampaignAdapter {

    private ListCampaignUseCase delegate;

     public ListCampaignAdapter(CampaignRepository campaignRepository) {
         delegate = new ListCampaignUseCase(campaignRepository);
     }

     public List<CampaignResponse> list(PaginateRequest request) {
         List<Campaign> list =  delegate.execute(request);
         System.out.println("list: " + list.size());
         return list.stream().map(CampaignDtoMapper.INSTANCE::toResponse).toList();
     }
}