package mg.tife.ads.application.usecase;

import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCampaignUseCaseTest {

    @Mock
    CampaignRepository campaignRepository;

    @Mock
    EventPublisher eventPublisher;

    @InjectMocks
    CreateCampaignUseCase useCase;

    @Test
    void execute_success() {
        Campaign campaign = mock(Campaign.class);
        UUID id = UUID.randomUUID();
        when(campaign.getId()).thenReturn(id);
        when(campaignRepository.save(campaign)).thenReturn(campaign);

        UUID result = useCase.execute(campaign);

        assertEquals(id, result);
        verify(campaignRepository).save(campaign);
        verify(eventPublisher).publishCampaignCreated(campaign);
    }
}
