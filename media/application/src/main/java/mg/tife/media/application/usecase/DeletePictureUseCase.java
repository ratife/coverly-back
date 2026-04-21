package mg.tife.media.application.usecase;

import mg.tife.media.domain.repository.PictureRepository;

import java.util.UUID;

public class DeletePictureUseCase {
    private final PictureRepository pictureRepository;

    public DeletePictureUseCase(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public void execute(UUID pictureId) {
        pictureRepository.findById(pictureId)
                .orElseThrow(() -> new IllegalArgumentException("Picture not found with id: " + pictureId));
        pictureRepository.delete(pictureId);
    }
}

