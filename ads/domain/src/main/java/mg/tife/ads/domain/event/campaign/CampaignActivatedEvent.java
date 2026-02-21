package mg.tife.ads.domain.event.campaign;

import java.time.LocalDateTime;
import java.util.UUID;

public record CampaignActivatedEvent(
        UUID campaignId,
        LocalDateTime occurredAt
) {}