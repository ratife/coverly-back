package mg.tife.ads.presentation.adapter.mission;

import mg.tife.ads.domain.model.mission.Mission;
import mg.tife.ads.domain.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RejectMissionAdapter {

    private final MissionRepository missionRepository;

    public RejectMissionAdapter(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public Mission reject(UUID missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));
        mission.reject();
        return missionRepository.save(mission);
    }
}

