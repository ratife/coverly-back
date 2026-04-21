package mg.tife.media.application.usecase;

import mg.tife.media.domain.Video;
import mg.tife.media.domain.repository.VideoRepository;

import java.util.Optional;
import java.util.UUID;

public class GetVideoUseCase {
    private final VideoRepository videoRepository;

    public GetVideoUseCase(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Optional<Video> execute(UUID videoId) {
        return videoRepository.findById(videoId);
    }
}

