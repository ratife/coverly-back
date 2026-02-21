package mg.tife.ads.domain.event.campaign;

import java.time.LocalDateTime;
import java.util.UUID;

public record BudgetExceededEvent(
        UUID campaignId,
        LocalDateTime occurredAt
) {}