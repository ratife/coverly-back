package mg.tife.ads.application.usecase;

import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.domain.repository.EventPublisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsumeBudgetUseCaseTest {

    @Mock
    CampaignRepository campaignRepository;

    @Mock
    EventPublisher eventPublisher;

    @InjectMocks
    ConsumeBudgetUseCase useCase;

    @Test
    void execute_success() {
        UUID id = UUID.randomUUID();
        Campaign campaign = mock(Campaign.class);
        BigDecimal amount = BigDecimal.valueOf(12.34);

        when(campaignRepository.findById(id)).thenReturn(Optional.of(campaign));
        when(campaignRepository.save(campaign)).thenReturn(campaign);

        useCase.execute(id, amount);

        verify(campaign).consumeBudget(amount);
        verify(campaignRepository).save(campaign);
    }

    @Test
    void execute_notFound_throws() {
        UUID id = UUID.randomUUID();
        when(campaignRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(java.util.NoSuchElementException.class, () -> useCase.execute(id, BigDecimal.ONE));
    }
}
