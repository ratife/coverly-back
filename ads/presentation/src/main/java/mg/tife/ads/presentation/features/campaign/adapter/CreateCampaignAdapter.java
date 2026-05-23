package mg.tife.ads.presentation.features.campaign.adapter;

import mg.tife.ads.application.features.campaign.CreateCampaignUseCase;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
public class CreateCampaignAdapter {
    private final CreateCampaignUseCase delegate;

    CreateCampaignAdapter(CampaignRepository campaignRepository, EventPublisher eventPublisher) {
        delegate = new CreateCampaignUseCase(campaignRepository, eventPublisher);
    }

    public UUID createCampaign(Campaign campaign) {
        System.out.println("CreateCampaignAdapter: creating campaign: " + campaign);
        return delegate.execute(campaign);
    }
}