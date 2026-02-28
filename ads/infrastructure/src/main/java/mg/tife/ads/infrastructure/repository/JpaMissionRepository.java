package mg.tife.ads.infrastructure.repository;

import mg.tife.ads.domain.model.mission.Mission;
import mg.tife.ads.domain.repository.MissionRepository;
import mg.tife.ads.infrastructure.mapper.MissionMapper;
import mg.tife.ads.infrastructure.entity.MissionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaMissionRepository implements MissionRepository {

    private final MissionEntityRepository delegate;

    public JpaMissionRepository(MissionEntityRepository delegate) {
        this.delegate = delegate;
    }

    @Override
    public Mission save(Mission mission) {
        MissionEntity entity = MissionMapper.INSTANCE.toEntity(mission);
        MissionEntity saved = delegate.save(entity);
        return MissionMapper.INSTANCE.toDomain(saved);
    }

    @Override
    public Optional<Mission> findById(UUID id) {
        return delegate.findById(id).map(MissionMapper.INSTANCE::toDomain);
    }

    @Override
    public List<Mission> findByCampaignId(UUID campaignId) {
        return delegate.findByCampaignId(campaignId)
                .stream()
                .map(MissionMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }
}

