package mg.tife.ads.presentation.features.campaign.dto.mapper;

import mg.tife.ads.domain.model.campaign.Budget;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.model.campaign.CommissionPolicy;
import mg.tife.ads.domain.model.campaign.MissionTemplate;
import mg.tife.ads.presentation.features.campaign.dto.request.CreateCampaignRequest;
import mg.tife.ads.presentation.features.campaign.dto.response.CampaignResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper
public interface CampaignDtoMapper {

    CampaignDtoMapper INSTANCE = Mappers.getMapper(CampaignDtoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "budget", source = "budget", qualifiedByName = "toBudget")
    @Mapping(target = "commissionPolicy", source = "platformCommissionRate", qualifiedByName = "toCommissionPolicy")
    Campaign toDomain(CreateCampaignRequest request);

    @Mapping(target = "totalBudget", source = "budget.total")
    @Mapping(target = "spentBudget", source = "budget.spent")
    @Mapping(target = "remainingBudget", source = "budget", qualifiedByName = "getRemainingBudget")
    @Mapping(target = "platformCommissionRate", source = "commissionPolicy.platformRate")
    CampaignResponse toResponse(Campaign campaign);


    @Named("toBudget")
    static Budget toBudget(BigDecimal total) {
        return new Budget(total);
    }

    @Named("toCommissionPolicy")
    static CommissionPolicy toCommissionPolicy(BigDecimal platformRate) {
        return new CommissionPolicy(platformRate);
    }

    @Named("getRemainingBudget")
    static BigDecimal getRemainingBudget(Budget budget) {
        if(budget == null) return BigDecimal.ZERO;
        return budget.remaining();
    }

    @Named("calculateTotalCost")
    static BigDecimal calculateTotalCost(MissionTemplate template) {
        return template.totalCost();
    }
}