package mg.tife.ads.presentation.adapter;

import mg.tife.ads.application.usecase.PauseCampaignUseCase;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PauseCampaignAdapter {
    private PauseCampaignUseCase delegate;

    public PauseCampaignAdapter(CampaignRepository campaignRepository,
                                EventPublisher eventPublisher) {
        delegate = new PauseCampaignUseCase(campaignRepository, eventPublisher);
    }

    public void pause(UUID uuid) {
        delegate.execute(uuid);
    }
}