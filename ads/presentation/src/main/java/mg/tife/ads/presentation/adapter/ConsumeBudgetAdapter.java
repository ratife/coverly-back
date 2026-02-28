package mg.tife.ads.presentation.adapter;

import mg.tife.ads.application.usecase.ConsumeBudgetUseCase;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ConsumeBudgetAdapter {

    private ConsumeBudgetUseCase delegate;

    public ConsumeBudgetAdapter(CampaignRepository campaignRepository,
                                EventPublisher eventPublisher) {
        delegate = new ConsumeBudgetUseCase(campaignRepository, eventPublisher);
    }

    public void consume(UUID campaignId, BigDecimal amount){
         delegate.execute(campaignId, amount);
    }

}
