package mg.tife.ads.presentation.adapter;

import mg.tife.ads.application.dto.PaginateRequest;
import mg.tife.ads.application.usecase.ListCampaignUseCase;
import mg.tife.ads.domain.repository.CampaignRepository;
import org.springframework.stereotype.Service;

@Service
public class ListCampaignAdapter {
    private ListCampaignUseCase delegate;

     public ListCampaignAdapter(CampaignRepository campaignRepository) {
         delegate = new ListCampaignUseCase(campaignRepository);
     }
     public void list(PaginateRequest request) {
         delegate.execute(request);
     }
}