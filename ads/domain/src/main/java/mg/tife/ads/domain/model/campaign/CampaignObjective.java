package mg.tife.ads.domain.model.campaign;

import lombok.Data;

import java.util.UUID;

@Data
public class CampaignObjective {
    private UUID id;
    private Integer nbrVue;
    private Integer nbrClick;
    private Integer nbrReact;
    private Integer nbrShare;
}