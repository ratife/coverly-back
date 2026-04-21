package mg.tife.ads.presentation.dto.mission.response;

import mg.tife.ads.domain.model.mission.MissionType;
import mg.tife.ads.domain.model.mission.MissionStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record MissionResponse(
        UUID id,
        UUID campaignId,
        UUID publisherId,
        MissionType type,
        BigDecimal price,
        MissionStatus status,
        LocalDate deadline,
        LocalDateTime createdAt,
        LocalDateTime validatedAt,
        ProofResponse proof
) {
}

