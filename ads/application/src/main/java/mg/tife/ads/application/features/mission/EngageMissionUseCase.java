package mg.tife.ads.application.features.mission;
import mg.tife.ads.domain.event.mission.MissionEngagedEvent;
import mg.tife.ads.domain.model.mission.Mission;
import mg.tife.ads.domain.model.mission.MissionStatus;
import mg.tife.ads.domain.repository.EventPublisher;
import mg.tife.ads.domain.repository.MissionRepository;

import java.util.UUID;
import java.util.logging.Logger;

public class EngageMissionUseCase {

    private final MissionRepository missionRepository;
    private final EventPublisher eventPublisher;
    private final Logger logger = Logger.getLogger(EngageMissionUseCase.class.getName());

    public EngageMissionUseCase(
            MissionRepository missRep,
            EventPublisher eventPublisher
    ) {
        this.missionRepository = missRep;
        this.eventPublisher = eventPublisher;
    }

    public UUID execute(UUID missionId,UUID publisherId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow();
        logger.info("Executing EngageMissionUseCase with mission: " + mission);
        mission.setPublisherId(publisherId);
        mission.setStatus(MissionStatus.SUBMITTED);
        mission = missionRepository.save(mission);
        eventPublisher.publishMissionEvent(new MissionEngagedEvent(mission));
        logger.info("Mission engaged successfully with ID: " + mission.getId() + " for publisher: " + publisherId);
        return mission.getId();
    }
}