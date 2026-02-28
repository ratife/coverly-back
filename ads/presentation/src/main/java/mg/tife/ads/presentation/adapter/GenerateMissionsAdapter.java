package mg.tife.ads.presentation.adapter;

import mg.tife.ads.application.usecase.GenerateMissionsUseCase;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenerateMissionsAdapter {
    private GenerateMissionsUseCase delegate;

     public GenerateMissionsAdapter(CampaignRepository campaignRepository, MissionRepository missionRepository) {
         delegate = new GenerateMissionsUseCase(campaignRepository, missionRepository);
     }
     public void generate(UUID campaignId) {
         delegate.execute(campaignId);
     }
}