package mg.tife.ads.infrastructure.mapper;

import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.infrastructure.entity.CampaignEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CampaignMapper {
    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);
    Campaign toDomain(CampaignEntity campaignEntity);
    CampaignEntity toEntity(Campaign campaign);
}
