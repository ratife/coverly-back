package mg.tife.ads.domain.model.mission;

import java.time.LocalDateTime;

public class Proof {

    private final String url;
    private final LocalDateTime submittedAt;

    public Proof(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("Proof URL required");
        }
        this.url = url;
        this.submittedAt = LocalDateTime.now();
    }

    public String getUrl() {
        return url;
    }
}
