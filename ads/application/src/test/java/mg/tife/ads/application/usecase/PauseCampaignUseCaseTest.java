package mg.tife.ads.application.usecase;
/*
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

 */
class PauseCampaignUseCaseTest {
/*
    @Mock
    CampaignRepository campaignRepository;

    @Mock
    EventPublisher eventPublisher;

    @InjectMocks
    PauseCampaignUseCase useCase;

    @Test
    void execute_success() {
        UUID id = UUID.randomUUID();
        Campaign campaign = mock(Campaign.class);

        when(campaignRepository.findById(id)).thenReturn(Optional.of(campaign));
        when(campaignRepository.save(campaign)).thenReturn(campaign);

        useCase.execute(id);

        verify(campaign).pause();
        verify(campaignRepository).save(campaign);
    }

    @Test
    void execute_notFound_throws() {
        UUID id = UUID.randomUUID();
        when(campaignRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(java.util.NoSuchElementException.class, () -> useCase.execute(id));
    }

 */
}
