package mg.tife.ads.domain.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Event {
    private LocalDateTime eventDate;
    public Event(){
        eventDate = LocalDateTime.now();
    }
}