package mg.tife.ads.presentation.dto.mission.response;

import java.time.LocalDateTime;

public record ProofResponse(
        String url,
        LocalDateTime submittedAt
) {
}

