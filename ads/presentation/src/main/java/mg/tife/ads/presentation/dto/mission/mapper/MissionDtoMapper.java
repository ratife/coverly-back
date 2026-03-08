package mg.tife.ads.presentation.dto.mission.mapper;

import mg.tife.ads.domain.model.mission.Mission;
import mg.tife.ads.domain.model.mission.MissionPrice;
import mg.tife.ads.domain.model.mission.Proof;
import mg.tife.ads.presentation.dto.mission.request.CreateMissionRequest;
import mg.tife.ads.presentation.dto.mission.response.MissionResponse;
import mg.tife.ads.presentation.dto.mission.response.ProofResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MissionDtoMapper {

    MissionDtoMapper INSTANCE = Mappers.getMapper(MissionDtoMapper.class);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "price", source = "price", qualifiedByName = "toMissionPrice")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "proof", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "validatedAt", ignore = true)
    Mission toDomain(CreateMissionRequest request);

    @Mapping(target = "price", source = "price.amount")
    @Mapping(target = "proof", source = "proof", qualifiedByName = "toProofResponse")
    MissionResponse toResponse(Mission mission);

    @Named("toMissionPrice")
    static MissionPrice toMissionPrice(java.math.BigDecimal price) {
        return new MissionPrice(price);
    }

    @Named("toProofResponse")
    static ProofResponse toProofResponse(Proof proof) {
        if (proof == null) {
            return null;
        }
        return new ProofResponse(proof.getUrl(), null);
    }
}

