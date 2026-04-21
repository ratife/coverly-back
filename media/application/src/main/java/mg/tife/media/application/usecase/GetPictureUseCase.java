package mg.tife.media.application.usecase;

import mg.tife.media.domain.Picture;
import mg.tife.media.domain.repository.PictureRepository;

import java.util.Optional;
import java.util.UUID;

public class GetPictureUseCase {
    private final PictureRepository pictureRepository;

    public GetPictureUseCase(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Optional<Picture> execute(UUID pictureId) {
        return pictureRepository.findById(pictureId);
    }
}

