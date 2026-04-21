package mg.tife.ads.presentation.dto.campaign.request;

import java.math.BigDecimal;

public record ConsumeBudgetRequest(
        BigDecimal amount
) {
}

