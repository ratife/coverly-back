package mg.tife.ads.domain.event.campaign;

import lombok.Getter;
import mg.tife.ads.domain.event.Event;
import mg.tife.ads.domain.model.campaign.Campaign;

import java.util.UUID;

@Getter
public class CampaignEvent extends Event {
    private final UUID campaignId;
    public CampaignEvent(Campaign campaign){
        super();
        campaignId = campaign.getId();
    }
}