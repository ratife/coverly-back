package mg.tife.media.application.usecase;

import mg.tife.media.domain.Picture;
import mg.tife.media.domain.repository.PictureRepository;

import java.util.UUID;

public class UpdatePictureUseCase {
    private final PictureRepository pictureRepository;

    public UpdatePictureUseCase(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public UUID execute(UUID pictureId, Picture updatedPicture) {
        Picture picture = pictureRepository.findById(pictureId)
                .orElseThrow(() -> new IllegalArgumentException("Picture not found with id: " + pictureId));
        
        updatedPicture.setId(pictureId);
        Picture saved = pictureRepository.save(updatedPicture);
        return saved.getId();
    }
}

