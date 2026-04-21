package mg.tife.ads.presentation.dto.campaign.response;

import mg.tife.ads.domain.model.campaign.CampaignObjective;
import mg.tife.ads.domain.model.campaign.CampaignStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CampaignResponse(
        UUID id,
        UUID advertiserId,
        String name,
        CampaignObjective objective,
        CampaignStatus status,
        BigDecimal totalBudget,
        BigDecimal spentBudget,
        BigDecimal remainingBudget,
        BigDecimal platformCommissionRate,
        LocalDate startDate,
        LocalDate endDate
) {
}

