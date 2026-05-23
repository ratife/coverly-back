package mg.tife.ads.presentation.features.campaign.adapter;

import mg.tife.ads.application.features.campaign.PauseCampaignUseCase;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PauseCampaignAdapter {
    private final PauseCampaignUseCase delegate;

    public PauseCampaignAdapter(CampaignRepository campaignRepository,
                                EventPublisher eventPublisher) {
        delegate = new PauseCampaignUseCase(campaignRepository, eventPublisher);
    }

    public void pause(UUID uuid) {
        delegate.execute(uuid);
    }
}