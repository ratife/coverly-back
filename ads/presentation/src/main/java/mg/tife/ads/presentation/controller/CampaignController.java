package mg.tife.ads.presentation.controller;

import mg.tife.ads.application.usecase.*;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.presentation.adapter.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final ActivateCampaignAdapter activateCampaignAdapter;
    private final ConsumeBudgetAdapter consumeBudgetAdapter;
    private final CreateCampaignAdapter createCampaignAdapter;
    private final GenerateMissionsAdapter generateMissionsAdapter;
    private final ListCampaignAdapter listCampaignAdapter;
    private final PauseCampaignAdapter pauseCampaignAdapter;


    CampaignController(ActivateCampaignAdapter activateCampaignAdapter,
                       ConsumeBudgetAdapter consumeBudgetAdapter,
                       CreateCampaignAdapter createCampaignAdapter,
                       GenerateMissionsAdapter generateMissionsAdapter,
                       ListCampaignAdapter listCampaignAdapter,
                       PauseCampaignAdapter pauseCampaignAdapter) {
        this.activateCampaignAdapter = activateCampaignAdapter;
        this.consumeBudgetAdapter = consumeBudgetAdapter;
        this.createCampaignAdapter = createCampaignAdapter;
        this.generateMissionsAdapter = generateMissionsAdapter;
        this.listCampaignAdapter = listCampaignAdapter;
        this.pauseCampaignAdapter = pauseCampaignAdapter;
    }

    @PostMapping
    public ResponseEntity<UUID> createCampaign(@RequestBody Campaign campaign) {
        if (createCampaignAdapter == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        UUID id = createCampaignAdapter.createCampaign(campaign);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<UUID> activate(@PathVariable UUID id) {
        if (activateCampaignAdapter == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        UUID result = activateCampaignAdapter.active(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}/pause")
    public ResponseEntity<Void> pause(@PathVariable UUID id) {
        if (pauseCampaignAdapter == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        pauseCampaignAdapter.pause(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/consume")
    public ResponseEntity<Void> consume(@PathVariable UUID id, @RequestParam BigDecimal amount) {
        if (consumeBudgetAdapter == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        consumeBudgetAdapter.consume(id, amount);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/generate-missions")
    public ResponseEntity<Void> generateMissions(@PathVariable UUID id) {
        if (generateMissionsAdapter == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        generateMissionsAdapter.generate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public String test() {
        return "ok";
    }
}