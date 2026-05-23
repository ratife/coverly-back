package mg.tife.ads.presentation.features.mission.adapter;

import mg.tife.ads.application.features.mission.GenerateMissionsUseCase;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenerateMissionsAdapter {
    private final GenerateMissionsUseCase delegate;
     public GenerateMissionsAdapter(CampaignRepository campaignRepository, MissionRepository missionRepository) {
         delegate = new GenerateMissionsUseCase(campaignRepository, missionRepository);
     }
     public void generate(UUID campaignId) {
         delegate.execute(campaignId);
     }
}