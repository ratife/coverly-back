package mg.tife.media.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Picture {
    private UUID id;
    private String title;
    private int durationSeconds;
    private String url;
    private UUID campaignId;
}