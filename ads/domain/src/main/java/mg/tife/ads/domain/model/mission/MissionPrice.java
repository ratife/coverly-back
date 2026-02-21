package mg.tife.ads.domain.model.mission;

import java.math.BigDecimal;

public class MissionPrice {

    private final BigDecimal amount;

    public MissionPrice(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
