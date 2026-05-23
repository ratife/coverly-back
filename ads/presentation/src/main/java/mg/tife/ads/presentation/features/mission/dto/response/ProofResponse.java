package mg.tife.ads.presentation.features.mission.dto.response;

import java.time.LocalDateTime;

public record ProofResponse(
        String url,
        LocalDateTime submittedAt
) {
}

