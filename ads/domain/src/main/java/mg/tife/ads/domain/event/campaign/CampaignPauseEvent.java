package mg.tife.ads.domain.event.campaign;

import mg.tife.ads.domain.model.campaign.Campaign;

public class CampaignPauseEvent extends  CampaignEvent{
    public CampaignPauseEvent(Campaign campaign) {
        super(campaign);
    }
}