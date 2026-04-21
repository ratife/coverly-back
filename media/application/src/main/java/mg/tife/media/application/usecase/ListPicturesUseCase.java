package mg.tife.media.application.usecase;

import mg.tife.media.domain.Picture;
import mg.tife.media.domain.repository.PictureRepository;

import java.util.List;

public class ListPicturesUseCase {
    private final PictureRepository pictureRepository;

    public ListPicturesUseCase(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public List<Picture> execute() {
        return pictureRepository.findAll();
    }
}

