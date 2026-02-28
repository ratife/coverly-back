package mg.tife.ads.infrastructure.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CampaignEventListener {

    @EventListener
    public void handleCreated(CampaignCreatedEvent event) {
        System.out.println("Campaign created: " + event.campaign().getId());
    }

    @EventListener
    public void handleActivated(CampaignActivatedEvent event) {
        System.out.println("Campaign activated: " + event.campaign().getId());
    }
}