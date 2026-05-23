package mg.tife.ads.presentation.features.campaign.adapter;

import mg.tife.ads.application.features.campaign.ActivateCampaignUseCase;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActivateCampaignAdapter {

    private final ActivateCampaignUseCase delegate;

    public ActivateCampaignAdapter(CampaignRepository campaignRepository,
                                   EventPublisher eventPublisher) {
        delegate = new ActivateCampaignUseCase(campaignRepository, eventPublisher);
    }

    public UUID active(UUID uuid) {
        return delegate.execute(uuid);
    }
}
