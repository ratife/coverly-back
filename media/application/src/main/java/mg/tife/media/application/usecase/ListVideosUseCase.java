package mg.tife.media.application.usecase;

import mg.tife.media.domain.Video;
import mg.tife.media.domain.repository.VideoRepository;

import java.util.List;

public class ListVideosUseCase {
    private final VideoRepository videoRepository;

    public ListVideosUseCase(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> execute() {
        return videoRepository.findAll();
    }
}

