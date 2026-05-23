package mg.tife.ads.presentation.features.campaign.dto.request;

import mg.tife.ads.domain.model.mission.MissionType;

import java.math.BigDecimal;

public record MissionTemplateRequest(
        MissionType type,
        BigDecimal unitPrice,
        int quantity
) {
}

