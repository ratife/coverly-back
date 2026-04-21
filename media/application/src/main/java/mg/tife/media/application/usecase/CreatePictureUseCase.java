package mg.tife.media.application.usecase;

import mg.tife.media.domain.Picture;
import mg.tife.media.domain.repository.PictureRepository;

import java.util.UUID;

public class CreatePictureUseCase {
    private final PictureRepository pictureRepository;

    public CreatePictureUseCase(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public UUID execute(Picture picture) {
        Picture saved = pictureRepository.save(picture);
        return saved.getId();
    }
}

