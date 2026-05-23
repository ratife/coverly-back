package mg.tife.ads.presentation.features.mission.adapter;

import mg.tife.ads.domain.model.mission.Mission;
import mg.tife.ads.domain.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateMissionAdapter {

    private final MissionRepository missionRepository;

    public CreateMissionAdapter(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public UUID create(Mission mission) {
        Mission saved = missionRepository.save(mission);
        return saved.getId();
    }
}

