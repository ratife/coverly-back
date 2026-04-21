package mg.tife.ads.presentation.adapter;

import mg.tife.ads.application.dto.PaginateRequest;
import mg.tife.ads.application.usecase.GetCampaignUseCase;
import mg.tife.ads.application.usecase.ListCampaignUseCase;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.presentation.dto.campaign.mapper.CampaignDtoMapper;
import mg.tife.ads.presentation.dto.campaign.response.CampaignResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GetCampaignAdapter {

    private GetCampaignUseCase delegate;

     public GetCampaignAdapter(CampaignRepository campaignRepository) {
         delegate = new GetCampaignUseCase(campaignRepository);
     }

     public Optional<CampaignResponse> get(UUID campaignId) {
         Optional<Campaign> res =  delegate.execute(campaignId);
         return Optional.of(CampaignDtoMapper.INSTANCE.toResponse(res.get()));
     }
}