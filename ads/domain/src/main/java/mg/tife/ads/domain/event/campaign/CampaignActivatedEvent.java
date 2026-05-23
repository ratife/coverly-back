package mg.tife.ads.domain.event.campaign;

import mg.tife.ads.domain.model.campaign.Campaign;

public class CampaignActivatedEvent extends CampaignEvent {
    public CampaignActivatedEvent(Campaign campaign) {
        super(campaign);
    }
}