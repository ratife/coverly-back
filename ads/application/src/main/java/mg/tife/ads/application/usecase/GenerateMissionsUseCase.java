package mg.tife.ads.application.usecase;

import mg.tife.ads.domain.model.campaign.MissionTemplate;
import mg.tife.ads.domain.model.mission.Mission;
import mg.tife.ads.domain.model.mission.MissionPrice;
import mg.tife.ads.domain.repository.MissionRepository;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;

import java.util.UUID;

public class GenerateMissionsUseCase {

    private final CampaignRepository campaignRepository;
    private final MissionRepository missionRepository;

    public GenerateMissionsUseCase(CampaignRepository campaignRepository, MissionRepository missionRepository) {
        this.campaignRepository = campaignRepository;
        this.missionRepository = missionRepository;
    }

    public void execute(UUID campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow();
        if (!campaign.isReady()) {
            throw new IllegalStateException("Campaign not ready");
        }
        for (MissionTemplate template : campaign.getMissionTemplates()) {
            for (int i = 0; i < template.getQuantity(); i++) {
                Mission mission = new Mission(
                        UUID.randomUUID(),
                        campaignId,
                        null, // pas encore assigné
                        template.getType(),
                        new MissionPrice(template.getUnitPrice()),
                        campaign.getEndDate()
                );
                missionRepository.save(mission);
            }
        }
    }
}