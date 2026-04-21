package mg.tife.media.application.usecase;

import mg.tife.media.domain.Video;
import mg.tife.media.domain.repository.VideoRepository;

import java.util.UUID;

public class CreateVideoUseCase {
    private final VideoRepository videoRepository;

    public CreateVideoUseCase(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public UUID execute(Video video) {
        Video saved = videoRepository.save(video);
        return saved.getId();
    }
}

