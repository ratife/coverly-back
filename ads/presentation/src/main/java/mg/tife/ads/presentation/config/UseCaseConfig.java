package mg.tife.ads.presentation.config;

import mg.tife.ads.application.usecase.*;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;
import mg.tife.ads.domain.repository.MissionRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    @ConditionalOnBean({CampaignRepository.class, EventPublisher.class})
    public CreateCampaignUseCase createCampaignUseCase(CampaignRepository campaignRepository, EventPublisher eventPublisher) {
        return new CreateCampaignUseCase(campaignRepository, eventPublisher);
    }

    @Bean
    @ConditionalOnBean({CampaignRepository.class, EventPublisher.class})
    public ActivateCampaignUseCase activateCampaignUseCase(CampaignRepository campaignRepository, EventPublisher eventPublisher) {
        return new ActivateCampaignUseCase(campaignRepository, eventPublisher);
    }

    @Bean
    @ConditionalOnBean({CampaignRepository.class, EventPublisher.class})
    public ConsumeBudgetUseCase consumeBudgetUseCase(CampaignRepository campaignRepository, EventPublisher eventPublisher) {
        return new ConsumeBudgetUseCase(campaignRepository, eventPublisher);
    }

    @Bean
    @ConditionalOnBean({CampaignRepository.class, EventPublisher.class})
    public PauseCampaignUseCase pauseCampaignUseCase(CampaignRepository campaignRepository, EventPublisher eventPublisher) {
        return new PauseCampaignUseCase(campaignRepository, eventPublisher);
    }

    @Bean
    @ConditionalOnBean({CampaignRepository.class, MissionRepository.class})
    public GenerateMissionsUseCase generateMissionsUseCase(CampaignRepository campaignRepository, MissionRepository missionRepository) {
        return new GenerateMissionsUseCase(campaignRepository, missionRepository);
    }
}
