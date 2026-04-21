package mg.tife.ads.presentation.adapter;

import mg.tife.ads.application.usecase.ActivateCampaignUseCase;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActivateCampaignAdapter {

    private ActivateCampaignUseCase delegate;

    public ActivateCampaignAdapter(CampaignRepository campaignRepository,
                                   EventPublisher eventPublisher) {
        delegate = new ActivateCampaignUseCase(campaignRepository, eventPublisher);
    }

    public UUID active(UUID uuid) {
        return delegate.execute(uuid);
    }
}
