package mg.tife.ads.domain.event.campaign;

import lombok.Getter;
import mg.tife.ads.domain.model.campaign.Campaign;

import java.math.BigDecimal;

@Getter
public class BudgetConsumedEvent extends CampaignEvent{
    private final BigDecimal amount;
    public BudgetConsumedEvent(Campaign campaign,BigDecimal amount) {
        super(campaign);
        this.amount = amount;
    }

}
