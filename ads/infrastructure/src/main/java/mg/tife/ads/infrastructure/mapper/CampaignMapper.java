package mg.tife.ads.infrastructure.mapper;

import mg.tife.ads.domain.model.campaign.*;
import mg.tife.ads.infrastructure.entity.CampaignEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CampaignMapper {

    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);

    @Mapping(target = "id",source = "id")
    @Mapping(target = "advertiserId",source = "advertiserId")
    @Mapping(target = "name",source = "name")
    @Mapping(target = "objective",source = "objective")
    @Mapping(target = "status",source = "status")
    @Mapping(target = "startDate",source = "startDate")
    @Mapping(target = "endDate",source = "endDate")
    @Mapping(target = "budget", source = "budget")
    Campaign toDomain(CampaignEntity campaignEntity);


    @Mapping(target = "id",source = "id")
    @Mapping(target = "advertiserId",source = "advertiserId")
    @Mapping(target = "name",source = "name")
    @Mapping(target = "objective",source = "objective")
    @Mapping(target = "status",source = "status")
    @Mapping(target = "startDate",source = "startDate")
    @Mapping(target = "endDate",source = "endDate")
    @Mapping(target = "budget", source = "budget")
    CampaignEntity toEntity(Campaign campaign);
}