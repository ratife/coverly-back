package mg.tife.ads.domain.event.mission;

import lombok.Getter;
import mg.tife.ads.domain.event.Event;
import mg.tife.ads.domain.model.mission.Mission;

@Getter
public class MissionEvent extends Event {
    private final Mission mission;
    public MissionEvent(Mission mission) {
        this.mission = mission;
    }
}