package mg.tife.ads.presentation.features.campaign.adapter;

import mg.tife.ads.application.features.campaign.UpdateCampaignUseCase;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
public class UpdateCampaignAdapter {
    final private UpdateCampaignUseCase delegate;

    UpdateCampaignAdapter(CampaignRepository campaignRepository, EventPublisher eventPublisher) {
        delegate = new UpdateCampaignUseCase(campaignRepository, eventPublisher);
    }

    public UUID updateCampaign(Campaign campaign) {
        System.out.println("CreateCampaignAdapter: creating campaign: " + campaign);
        return delegate.execute(campaign);
    }
}