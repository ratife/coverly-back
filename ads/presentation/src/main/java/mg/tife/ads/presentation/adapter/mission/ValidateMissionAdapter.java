package mg.tife.ads.presentation.adapter.mission;

import mg.tife.ads.domain.model.mission.Mission;
import mg.tife.ads.domain.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ValidateMissionAdapter {

    private final MissionRepository missionRepository;

    public ValidateMissionAdapter(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public Mission validate(UUID missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));
        mission.validate();
        return missionRepository.save(mission);
    }
}

