package mg.tife.ads.domain.repository;

import mg.tife.ads.domain.model.campaign.Campaign;

public interface EventPublisher {
   void publishCampaignCreated(Campaign campaign);

    void publishCampaignActivated(Campaign campaign);

    void publishCampaignPaused();

    void publishCampaignUpdated(Campaign old,Campaign newCamp);
}
