package mg.tife.ads.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.EventPublisher;
import mg.tife.ads.infrastructure.event.CampaignActivatedEvent;
import mg.tife.ads.infrastructure.event.CampaignCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringEventPublisher implements EventPublisher {

    //private final ApplicationEventPublisher publisher;

    @Override
    public void publishCampaignCreated(Campaign campaign) {
        System.out.println("publish campagn");
        //publisher.publishEvent(new CampaignCreatedEvent(campaign));
    }

    @Override
    public void publishCampaignActivated(Campaign campaign) {
        System.out.println("active campagn");
        //publisher.publishEvent(new CampaignActivatedEvent(campaign));
    }
}