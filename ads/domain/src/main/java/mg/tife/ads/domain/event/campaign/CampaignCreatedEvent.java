package mg.tife.ads.domain.event.campaign;

import java.time.LocalDateTime;
import java.util.UUID;

public record CampaignCreatedEvent(
        UUID campaignId,
        LocalDateTime occurredAt
) {}