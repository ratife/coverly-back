package mg.tife.ads.infrastructure.mapper;

import mg.tife.ads.domain.model.mission.Mission;
import mg.tife.ads.infrastructure.entity.MissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MissionMapper {
    MissionMapper INSTANCE = Mappers.getMapper(MissionMapper.class);
    Mission toDomain(MissionEntity missionEntity);
    MissionEntity toEntity(Mission mission);
}

