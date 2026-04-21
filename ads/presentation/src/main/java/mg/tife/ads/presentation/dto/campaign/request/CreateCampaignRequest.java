package mg.tife.ads.presentation.dto.campaign.request;

import mg.tife.ads.domain.model.campaign.CampaignObjective;
import mg.tife.ads.presentation.dto.campaign.request.MissionTemplateRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CreateCampaignRequest(
        UUID advertiserId,
        String name,
        CampaignObjective objective,
        BigDecimal budget,
        BigDecimal platformCommissionRate,
        LocalDate startDate,
        LocalDate endDate,
        List<MissionTemplateRequest> missionTemplates
) {
}

