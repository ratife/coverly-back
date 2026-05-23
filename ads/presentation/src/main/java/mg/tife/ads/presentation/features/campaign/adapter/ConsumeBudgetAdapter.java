package mg.tife.ads.presentation.features.campaign.adapter;

import mg.tife.ads.application.features.campaign.ConsumeBudgetUseCase;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ConsumeBudgetAdapter {

    private final ConsumeBudgetUseCase delegate;

    public ConsumeBudgetAdapter(CampaignRepository campaignRepository,
                                EventPublisher eventPublisher) {
        delegate = new ConsumeBudgetUseCase(campaignRepository, eventPublisher);
    }

    public void consume(UUID campaignId, BigDecimal amount){
         delegate.execute(campaignId, amount);
    }

}
