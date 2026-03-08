package mg.tife.ads.application.usecase;
/*
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.model.campaign.MissionTemplate;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.MissionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.math.BigDecimal;
import mg.tife.ads.domain.model.mission.MissionType;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

 */
class GenerateMissionsUseCaseTest {
/*
    @Mock
    CampaignRepository campaignRepository;

    @Mock
    MissionRepository missionRepository;

    @InjectMocks
    GenerateMissionsUseCase useCase;

    @Test
    void execute_generatesMissions_whenCampaignReady() {
        UUID campaignId = UUID.randomUUID();
        Campaign campaign = mock(Campaign.class);
        MissionTemplate template = mock(MissionTemplate.class);

        when(campaignRepository.findById(campaignId)).thenReturn(Optional.of(campaign));
        when(campaign.isReady()).thenReturn(true);
    when(template.getQuantity()).thenReturn(3);
    // provide a unit price and type to avoid NullPointerException when creating MissionPrice
    when(template.getUnitPrice()).thenReturn(BigDecimal.ONE);
    when(template.getType()).thenReturn(MissionType.POST);
        when(campaign.getMissionTemplates()).thenReturn(Collections.singletonList(template));

        useCase.execute(campaignId);

        verify(missionRepository, times(3)).save(any());
    }

    @Test
    void execute_throws_whenNotReady() {
        UUID campaignId = UUID.randomUUID();
        Campaign campaign = mock(Campaign.class);

        when(campaignRepository.findById(campaignId)).thenReturn(Optional.of(campaign));
        when(campaign.isReady()).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> useCase.execute(campaignId));
        verify(missionRepository, times(0)).save(any());
    }

 */
}
