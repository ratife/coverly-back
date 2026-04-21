package mg.tife.ads.presentation.dto.campaign.request;

import mg.tife.ads.domain.model.mission.MissionType;

import java.math.BigDecimal;

public record MissionTemplateRequest(
        MissionType type,
        BigDecimal unitPrice,
        int quantity
) {
}

