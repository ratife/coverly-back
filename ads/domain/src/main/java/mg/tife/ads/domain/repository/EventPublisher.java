package mg.tife.ads.domain.repository;

import mg.tife.ads.domain.event.campaign.CampaignEvent;
import mg.tife.ads.domain.event.mission.MissionEvent;

public interface EventPublisher {
    void publishCampaignEvent(CampaignEvent event);
    void publishMissionEvent(MissionEvent event);
}
