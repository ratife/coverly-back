package mg.tife.ads.domain.model.campaign;

import lombok.Data;
import mg.tife.ads.domain.model.mission.MissionType;

import java.math.BigDecimal;

@Data
public class MissionTemplate {

    private final MissionType type;
    private final BigDecimal unitPrice;
    private final int quantity;

    public MissionTemplate(MissionType type, BigDecimal unitPrice, int quantity) {
        this.type = type;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public BigDecimal totalCost() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}