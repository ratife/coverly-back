package mg.tife.ads.presentation.features.campaign;

import mg.tife.ads.application.dto.PaginateRequest;
import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.presentation.features.campaign.adapter.*;
import mg.tife.ads.presentation.features.mission.adapter.GenerateMissionsAdapter;
import mg.tife.ads.presentation.features.campaign.dto.mapper.CampaignDtoMapper;
import mg.tife.ads.presentation.features.campaign.dto.request.ConsumeBudgetRequest;
import mg.tife.ads.presentation.features.campaign.dto.request.CreateCampaignRequest;
import mg.tife.ads.presentation.features.campaign.dto.response.CampaignResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final ActivateCampaignAdapter activateCampaignAdapter;
    private final ConsumeBudgetAdapter consumeBudgetAdapter;
    private final CreateCampaignAdapter createCampaignAdapter;
    private final UpdateCampaignAdapter updateCampaignAdapter;
    private final GenerateMissionsAdapter generateMissionsAdapter;
    private final PauseCampaignAdapter pauseCampaignAdapter;
    private final ListCampaignAdapter listCampaignAdapter;
    private final GetCampaignAdapter getCampaignAdapter;


    CampaignController(ActivateCampaignAdapter activateCampaignAdapter,
                       ConsumeBudgetAdapter consumeBudgetAdapter,
                       CreateCampaignAdapter createCampaignAdapter, UpdateCampaignAdapter updateCampaignAdapter,
                       GenerateMissionsAdapter generateMissionsAdapter,
                       PauseCampaignAdapter pauseCampaignAdapter,
                       ListCampaignAdapter listCampaignAdapter,
                       GetCampaignAdapter getCampaignAdapter) {
        this.activateCampaignAdapter = activateCampaignAdapter;
        this.consumeBudgetAdapter = consumeBudgetAdapter;
        this.createCampaignAdapter = createCampaignAdapter;
        this.updateCampaignAdapter = updateCampaignAdapter;
        this.generateMissionsAdapter = generateMissionsAdapter;
        this.pauseCampaignAdapter = pauseCampaignAdapter;
        this.listCampaignAdapter = listCampaignAdapter;
        this.getCampaignAdapter = getCampaignAdapter;
    }

    @PostMapping
    public ResponseEntity<UUID> createCampaign(@RequestBody CreateCampaignRequest request) {
        System.out.println("Received create campaign request: " + request);
        if (createCampaignAdapter == null || updateCampaignAdapter == null)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        Campaign campaign = CampaignDtoMapper.INSTANCE.toDomain(request);
        UUID id;
        if(request.id() != null) {
            id = updateCampaignAdapter.updateCampaign(campaign);
        }
        else{
            id = createCampaignAdapter.createCampaign(campaign);
        }
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
    public ResponseEntity<Void> consume(@PathVariable UUID id, @RequestBody ConsumeBudgetRequest request) {
        if (consumeBudgetAdapter == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        consumeBudgetAdapter.consume(id, request.amount());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/generate-missions")
    public ResponseEntity<Void> generateMissions(@PathVariable UUID id) {
        if (generateMissionsAdapter == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        generateMissionsAdapter.generate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CampaignResponse>> getCampaigns(PaginateRequest request) {
        List<CampaignResponse> camps = this.listCampaignAdapter.list(request);
        System.out.println("Camps: " + camps.size());
        return camps.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(camps);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignResponse> getCampaign(UUID campaignId) {
        Optional<CampaignResponse> res = this.getCampaignAdapter.get(campaignId);
        return res.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}