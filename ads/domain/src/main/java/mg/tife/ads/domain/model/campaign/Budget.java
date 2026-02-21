package mg.tife.ads.domain.model.campaign;

import java.math.BigDecimal;

public class Budget {

    private final BigDecimal total;
    private BigDecimal spent;

    public Budget(BigDecimal total) {
        if (total.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Budget must be positive");
        }
        this.total = total;
        this.spent = BigDecimal.ZERO;
    }

    public void consume(BigDecimal amount) {
        if (remaining().compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient budget");
        }
        this.spent = this.spent.add(amount);
    }

    public BigDecimal remaining() {
        return total.subtract(spent);
    }
}