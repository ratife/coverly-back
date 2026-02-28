package mg.tife.ads.presentation.adapter;

import mg.tife.ads.application.usecase.ActivateCampaignUseCase;
import mg.tife.ads.application.usecase.CreateCampaignUseCase;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CreateCampaignAdapter {
    private CreateCampaignUseCase delegate;

    CreateCampaignAdapter(CampaignRepository campaignRepository, EventPublisher eventPublisher) {
        delegate = new CreateCampaignUseCase(campaignRepository, eventPublisher);
    }

    public UUID createCampaign(Campaign campaign) {
        return delegate.execute(campaign);
    }
}