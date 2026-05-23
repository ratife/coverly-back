package mg.tife.ads.presentation.features.mission;

import mg.tife.ads.domain.model.mission.Mission;
import mg.tife.ads.presentation.features.mission.adapter.*;
import mg.tife.ads.presentation.features.mission.dto.mapper.MissionDtoMapper;
import mg.tife.ads.presentation.features.mission.dto.request.CreateMissionRequest;
import mg.tife.ads.presentation.features.mission.dto.request.EngageMissionRequest;
import mg.tife.ads.presentation.features.mission.dto.request.SubmitProofRequest;
import mg.tife.ads.presentation.features.mission.dto.response.MissionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    private final CreateMissionAdapter createMissionAdapter;
    private final AcceptMissionAdapter acceptMissionAdapter;
    private final SubmitProofAdapter submitProofAdapter;
    private final ValidateMissionAdapter validateMissionAdapter;
    private final RejectMissionAdapter rejectMissionAdapter;
    private final EngageMissionAdapter engageMissionAdapter;

    public MissionController(CreateMissionAdapter createMissionAdapter,
                           AcceptMissionAdapter acceptMissionAdapter,
                           SubmitProofAdapter submitProofAdapter,
                           ValidateMissionAdapter validateMissionAdapter,
                           RejectMissionAdapter rejectMissionAdapter, EngageMissionAdapter engageMissionAdapter) {
        this.createMissionAdapter = createMissionAdapter;
        this.acceptMissionAdapter = acceptMissionAdapter;
        this.submitProofAdapter = submitProofAdapter;
        this.validateMissionAdapter = validateMissionAdapter;
        this.rejectMissionAdapter = rejectMissionAdapter;
        this.engageMissionAdapter = engageMissionAdapter;
    }

    @PostMapping
    public ResponseEntity<UUID> createMission(@RequestBody CreateMissionRequest request) {
        if (createMissionAdapter == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        Mission mission = MissionDtoMapper.INSTANCE.toDomain(request);
        UUID id = createMissionAdapter.create(mission);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<MissionResponse> acceptMission(@PathVariable UUID id) {
        if (acceptMissionAdapter == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        Mission mission = acceptMissionAdapter.accept(id);
        MissionResponse response = MissionDtoMapper.INSTANCE.toResponse(mission);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/submit-proof")
    public ResponseEntity<MissionResponse> submitProof(@PathVariable UUID id, @RequestBody SubmitProofRequest request) {
        if (submitProofAdapter == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        Mission mission = submitProofAdapter.submit(id, request.proofUrl());
        MissionResponse response = MissionDtoMapper.INSTANCE.toResponse(mission);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/validate")
    public ResponseEntity<MissionResponse> validateMission(@PathVariable UUID id) {
        if (validateMissionAdapter == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        Mission mission = validateMissionAdapter.validate(id);
        MissionResponse response = MissionDtoMapper.INSTANCE.toResponse(mission);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<MissionResponse> rejectMission(@PathVariable UUID id) {
        if (rejectMissionAdapter == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        Mission mission = rejectMissionAdapter.reject(id);
        MissionResponse response = MissionDtoMapper.INSTANCE.toResponse(mission);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/engage")
    public ResponseEntity<UUID> engage(@RequestBody EngageMissionRequest engageRequest) {
        if (engageMissionAdapter == null) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        UUID missionId = engageMissionAdapter.engage(engageRequest.missionId(),engageRequest.publisherId());
        return ResponseEntity.ok(missionId);
    }
}