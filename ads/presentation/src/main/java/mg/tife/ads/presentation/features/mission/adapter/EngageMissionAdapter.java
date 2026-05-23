package mg.tife.ads.presentation.features.mission.adapter;
import mg.tife.ads.application.features.mission.EngageMissionUseCase;
import mg.tife.ads.domain.repository.EventPublisher;
import mg.tife.ads.domain.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EngageMissionAdapter {
    private final EngageMissionUseCase delegate;
    public EngageMissionAdapter(MissionRepository missionRep,
                                EventPublisher eventPublisher) {
        delegate = new EngageMissionUseCase(missionRep, eventPublisher);
    }
    public UUID engage(UUID missionId, UUID publisherId) {
        return delegate.execute(missionId,publisherId);
    }
}
