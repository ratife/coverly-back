package mg.tife.ads.infrastructure.mapper;

import mg.tife.ads.domain.model.mission.Mission;
import mg.tife.ads.domain.model.mission.MissionPrice;
import mg.tife.ads.domain.model.mission.MissionStatus;
import mg.tife.ads.domain.model.mission.MissionType;
import mg.tife.ads.domain.model.mission.Proof;
import mg.tife.ads.infrastructure.entity.MissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MissionMapper {
    MissionMapper INSTANCE = Mappers.getMapper(MissionMapper.class);

    @Mapping(target = "type", source = "type", qualifiedByName = "toMissionType")
    @Mapping(target = "price", source = "price", qualifiedByName = "toMissionPrice")
    @Mapping(target = "status", source = "status", qualifiedByName = "toMissionStatus")
    @Mapping(target = "proof", source = ".", qualifiedByName = "toProof")
    Mission toDomain(MissionEntity missionEntity);

    @Mapping(target = "type", source = "type", qualifiedByName = "toMissionTypeEntity")
    @Mapping(target = "price", source = "price.amount")
    @Mapping(target = "status", source = "status", qualifiedByName = "toMissionStatusEntity")
    @Mapping(target = "proofUrl", ignore = true)
    @Mapping(target = "proofSubmittedAt", ignore = true)
    MissionEntity toEntity(Mission mission);

    @Named("toMissionType")
    static MissionType toMissionType(MissionEntity.MissionTypeEntity type) {
        if (type == null) return null;
        return MissionType.valueOf(type.name());
    }

    @Named("toMissionTypeEntity")
    static MissionEntity.MissionTypeEntity toMissionTypeEntity(MissionType type) {
        if (type == null) return null;
        return MissionEntity.MissionTypeEntity.valueOf(type.name());
    }

    @Named("toMissionPrice")
    static MissionPrice toMissionPrice(java.math.BigDecimal price) {
        if (price == null) return null;
        return new MissionPrice(price);
    }

    @Named("toMissionStatus")
    static MissionStatus toMissionStatus(MissionEntity.MissionStatusEntity status) {
        if (status == null) return null;
        return MissionStatus.valueOf(status.name());
    }

    @Named("toMissionStatusEntity")
    static MissionEntity.MissionStatusEntity toMissionStatusEntity(MissionStatus status) {
        if (status == null) return null;
        return MissionEntity.MissionStatusEntity.valueOf(status.name());
    }

    @Named("toProof")
    static Proof toProof(MissionEntity entity) {
        if (entity.getProofUrl() == null) return null;
        return new Proof(entity.getProofUrl());
    }
}

