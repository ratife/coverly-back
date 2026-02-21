package mg.tife.ads.domain.model.mission;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Mission {

    private UUID id;
    private UUID campaignId;
    private UUID publisherId;
    private MissionType type;
    private MissionPrice price;
    private MissionStatus status;
    private Proof proof;
    private LocalDate deadline;
    private LocalDateTime createdAt;
    private LocalDateTime validatedAt;

    public Mission(
            UUID id,
            UUID campaignId,
            UUID publisherId,
            MissionType type,
            MissionPrice price,
            LocalDate deadline
    ) {
        this.id = id;
        this.campaignId = campaignId;
        this.publisherId = publisherId;
        this.type = type;
        this.price = price;
        this.deadline = deadline;
        this.status = MissionStatus.CREATED;
        this.createdAt = LocalDateTime.now();
    }

    public void accept() {
        if (status != MissionStatus.CREATED) {
            throw new IllegalStateException("Mission cannot be accepted");
        }
        status = MissionStatus.ACCEPTED;
    }

    public void submitProof(Proof proof) {
        if (status != MissionStatus.ACCEPTED &&
                status != MissionStatus.IN_PROGRESS) {
            throw new IllegalStateException("Cannot submit proof");
        }
        this.proof = proof;
        this.status = MissionStatus.SUBMITTED;
    }

    public void validate() {
        if (status != MissionStatus.SUBMITTED) {
            throw new IllegalStateException("Mission must be submitted first");
        }
        this.status = MissionStatus.VALIDATED;
        this.validatedAt = LocalDateTime.now();
    }

    public void reject() {
        if (status != MissionStatus.SUBMITTED) {
            throw new IllegalStateException("Mission must be submitted first");
        }
        this.status = MissionStatus.REJECTED;
    }

    public void checkDeadline() {
        if (LocalDate.now().isAfter(deadline)) {
            throw new IllegalStateException("Mission expired");
        }
    }
}