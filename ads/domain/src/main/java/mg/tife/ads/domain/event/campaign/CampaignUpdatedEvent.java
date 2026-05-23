package mg.tife.ads.domain.event.campaign;
import lombok.Getter;
import mg.tife.ads.domain.model.campaign.Campaign;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class CampaignUpdatedEvent extends  CampaignEvent{
    private final List<Map<String,String>> fieldUpdated;
    public CampaignUpdatedEvent(Campaign newCampaign,Campaign oldCampaign) {
        super(newCampaign);
        fieldUpdated = new ArrayList<>();
        updateFieldUpdated(newCampaign,oldCampaign);
    }

    private void updateFieldUpdated(Campaign newCampaign,Campaign oldCampaign){
        if(!newCampaign.getAdvertiserId().equals(oldCampaign.getAdvertiserId())){
            fieldUpdated.add(Map.of("old_advertising",oldCampaign.getAdvertiserId().toString(),"new_advertising",newCampaign.getAdvertiserId().toString()));
        }
        if(!newCampaign.getName().equals(oldCampaign.getName())){
            fieldUpdated.add(Map.of("old_name",oldCampaign.getName(),"new_name",newCampaign.getName()));
        }
        if(!newCampaign.getObjective().equals(oldCampaign.getObjective())){
            fieldUpdated.add(Map.of("old_objective",oldCampaign.getObjective().toString(),"new_objective",newCampaign.getObjective().toString()));
        }
        /*
        if(!newCampaign.getStatus().equals(oldCampaign.getStatus())){
            fieldUpdated.add(Map.of("old_status",oldCampaign.getStatus().toString(),"new_status",newCampaign.getStatus().toString()));
        }
        if(!newCampaign.getTotalBudget().equals(oldCampaign.getTotalBudget())){
            fieldUpdated.add(Map.of("old_totalBudget",oldCampaign.getTotalBudget().toString(),"new_totalBudget",newCampaign.getTotalBudget().toString()));
        }
        if(!newCampaign.getSpentBudget().equals(oldCampaign.getSpentBudget())){
            fieldUpdated.add(Map.of("old_spentBudget",oldCampaign.getSpentBudget().toString(),"new_spentBudget",newCampaign.getSpentBudget().toString()));
        }
        if(!newCampaign.getRemainingBudget().equals(oldCampaign.getRemainingBudget())){
            fieldUpdated.add(Map.of("old_remainingBudget",oldCampaign.getRemainingBudget().toString(),"new_remainingBudget",newCampaign.getRemainingBudget().toString()));
        }
        if(!newCampaign.getPlatformCommissionRate().equals(oldCampaign.getPlatformCommissionRate())){
            fieldUpdated.add(Map.of("old_platformCommissionRate",oldCampaign.getPlatformCommissionRate().toString(),"new_platformCommissionRate",newCampaign.getPlatformCommissionRate().toString()));
        }
        */
        if(!newCampaign.getStartDate().equals(oldCampaign.getStartDate())){
            fieldUpdated.add(Map.of("old_startDate",oldCampaign.getStartDate().toString(),"new_startDate",newCampaign.getStartDate().toString()));
        }
        if(!newCampaign.getEndDate().equals(oldCampaign.getEndDate())){
            fieldUpdated.add(Map.of("old_endDate",oldCampaign.getEndDate().toString(),"new_endDate",newCampaign.getEndDate().toString()));
        }
    }
}