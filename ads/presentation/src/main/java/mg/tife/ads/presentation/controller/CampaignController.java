package mg.tife.ads.presentation.controller;

import mg.tife.ads.application.usecase.*;
import mg.tife.ads.domain.model.campaign.Campaign;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CreateCampaignUseCase createCampaignUseCase;
    private final ActivateCampaignUseCase activateCampaignUseCase;
    private final PauseCampaignUseCase pauseCampaignUseCase;
    private final ConsumeBudgetUseCase consumeBudgetUseCase;
    private final GenerateMissionsUseCase generateMissionsUseCase;

    public CampaignController(CreateCampaignUseCase createCampaignUseCase,
                              ActivateCampaignUseCase activateCampaignUseCase,
                              PauseCampaignUseCase pauseCampaignUseCase,
                              ConsumeBudgetUseCase consumeBudgetUseCase,
                              GenerateMissionsUseCase generateMissionsUseCase) {
        this.createCampaignUseCase = createCampaignUseCase;
        this.activateCampaignUseCase = activateCampaignUseCase;
        this.pauseCampaignUseCase = pauseCampaignUseCase;
        this.consumeBudgetUseCase = consumeBudgetUseCase;
        this.generateMissionsUseCase = generateMissionsUseCase;
    }

    @PostMapping
    public ResponseEntity<UUID> createCampaign(@RequestBody Campaign campaign) {
        if (createCampaignUseCase == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        UUID id = createCampaignUseCase.execute(campaign);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<UUID> activate(@PathVariable UUID id) {
        if (activateCampaignUseCase == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        UUID result = activateCampaignUseCase.execute(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}/pause")
    public ResponseEntity<Void> pause(@PathVariable UUID id) {
        if (pauseCampaignUseCase == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        pauseCampaignUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/consume")
    public ResponseEntity<Void> consume(@PathVariable UUID id, @RequestParam BigDecimal amount) {
        if (consumeBudgetUseCase == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        consumeBudgetUseCase.execute(id, amount);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/generate-missions")
    public ResponseEntity<Void> generateMissions(@PathVariable UUID id) {
        if (generateMissionsUseCase == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        generateMissionsUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
