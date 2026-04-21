package mg.tife.ads.presentation.dto.campaign.response;

import mg.tife.ads.domain.model.mission.MissionType;

import java.math.BigDecimal;

public record MissionTemplateResponse(
        MissionType type,
        BigDecimal unitPrice,
        int quantity,
        BigDecimal totalCost
) {
}

