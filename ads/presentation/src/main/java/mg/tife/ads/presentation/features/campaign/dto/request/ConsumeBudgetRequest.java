package mg.tife.ads.presentation.features.campaign.dto.request;

import java.math.BigDecimal;

public record ConsumeBudgetRequest(
        BigDecimal amount
) {
}

