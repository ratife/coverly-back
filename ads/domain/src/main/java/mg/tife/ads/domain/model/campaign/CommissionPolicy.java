package mg.tife.ads.domain.model.campaign;

import java.math.BigDecimal;

public class CommissionPolicy {

    private final BigDecimal platformRate; // ex: 0.30

    public CommissionPolicy(BigDecimal platformRate) {
        this.platformRate = platformRate;
    }

    public BigDecimal platformShare(BigDecimal amount) {
        return amount.multiply(platformRate);
    }

    public BigDecimal publisherShare(BigDecimal amount) {
        return amount.subtract(platformShare(amount));
    }
}