package mg.tife.ads.presentation.features.campaign.adapter;

import mg.tife.ads.application.features.campaign.GetCampaignUseCase;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.presentation.features.campaign.dto.mapper.CampaignDtoMapper;
import mg.tife.ads.presentation.features.campaign.dto.response.CampaignResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetCampaignAdapter {

    private final GetCampaignUseCase delegate;

     public GetCampaignAdapter(CampaignRepository campaignRepository) {
         delegate = new GetCampaignUseCase(campaignRepository);
     }

     public Optional<CampaignResponse> get(UUID campaignId) {
         Optional<Campaign> res =  delegate.execute(campaignId);
         return Optional.of(CampaignDtoMapper.INSTANCE.toResponse(res.orElse(null)));
     }
}