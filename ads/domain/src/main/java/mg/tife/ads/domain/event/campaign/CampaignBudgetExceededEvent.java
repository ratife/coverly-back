package mg.tife.ads.domain.event.campaign;

import mg.tife.ads.domain.model.campaign.Campaign;

public class CampaignBudgetExceededEvent extends CampaignEvent{
    public CampaignBudgetExceededEvent(Campaign campaign) {
        super(campaign);
    }
}