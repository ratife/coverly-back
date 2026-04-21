package mg.tife.ads.presentation.adapter.mission;

import mg.tife.ads.domain.model.mission.Mission;
import mg.tife.ads.domain.model.mission.Proof;
import mg.tife.ads.domain.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubmitProofAdapter {

    private final MissionRepository missionRepository;

    public SubmitProofAdapter(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public Mission submit(UUID missionId, String proofUrl) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));
        Proof proof = new Proof(proofUrl);
        mission.submitProof(proof);
        return missionRepository.save(mission);
    }
}

