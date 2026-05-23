package mg.tife.ads.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import mg.tife.ads.domain.event.campaign.CampaignEvent;
import mg.tife.ads.domain.event.mission.MissionEvent;
import mg.tife.ads.domain.repository.EventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringEventPublisher implements EventPublisher {

    @Override
    public void publishCampaignEvent(CampaignEvent event) {
        // Here you would integrate with your actual event publishing mechanism, such as Spring's ApplicationEventPublisher
        System.out.println("Publishing campaign event: " + event);
    }

    @Override
    public void publishMissionEvent(MissionEvent event) {
        // Here you would integrate with your actual event publishing mechanism, such as Spring's ApplicationEventPublisher
        System.out.println("Publishing mission event: " + event);
    }
}