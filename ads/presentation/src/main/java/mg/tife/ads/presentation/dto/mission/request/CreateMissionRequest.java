package mg.tife.ads.presentation.dto.mission.request;

import mg.tife.ads.domain.model.mission.MissionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateMissionRequest(
        UUID campaignId,
        UUID publisherId,
        MissionType type,
        BigDecimal price,
        LocalDate deadline
) {
}

