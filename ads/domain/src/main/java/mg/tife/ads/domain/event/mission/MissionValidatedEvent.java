package mg.tife.ads.domain.event.mission;

import java.math.BigDecimal;
import java.util.UUID;

public record MissionValidatedEvent(
        UUID missionId,
        UUID campaignId,
        BigDecimal amount
) {}