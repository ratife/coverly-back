package mg.tife.ads.presentation.features.mission.adapter;

import mg.tife.ads.domain.model.mission.Mission;
import mg.tife.ads.domain.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AcceptMissionAdapter {

    private final MissionRepository missionRepository;

    public AcceptMissionAdapter(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public Mission accept(UUID missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));
        mission.accept();
        return missionRepository.save(mission);
    }
}

