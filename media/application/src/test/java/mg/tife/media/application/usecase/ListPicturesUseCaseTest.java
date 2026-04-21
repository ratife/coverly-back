package mg.tife.media.application.usecase;

import mg.tife.media.domain.Picture;
import mg.tife.media.domain.repository.PictureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListPicturesUseCaseTest {

    @Mock
    private PictureRepository pictureRepository;

    private ListPicturesUseCase listPicturesUseCase;

    @BeforeEach
    void setUp() {
        listPicturesUseCase = new ListPicturesUseCase(pictureRepository);
    }

    @Test
    void execute_success_returns_all_pictures() {
        // Arrange
        Picture picture1 = new Picture();
        picture1.setTitle("Picture 1");

        Picture picture2 = new Picture();
        picture2.setTitle("Picture 2");

        List<Picture> pictures = Arrays.asList(picture1, picture2);
        when(pictureRepository.findAll()).thenReturn(pictures);

        // Act
        List<Picture> result = listPicturesUseCase.execute();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Picture 1", result.get(0).getTitle());
        assertEquals("Picture 2", result.get(1).getTitle());
        verify(pictureRepository).findAll();
    }

    @Test
    void execute_empty_list() {
        // Arrange
        when(pictureRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Picture> result = listPicturesUseCase.execute();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(pictureRepository).findAll();
    }

    @Test
    void execute_single_picture() {
        // Arrange
        Picture picture = new Picture();
        picture.setTitle("Single Picture");

        when(pictureRepository.findAll()).thenReturn(Arrays.asList(picture));

        // Act
        List<Picture> result = listPicturesUseCase.execute();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Single Picture", result.get(0).getTitle());
    }

    @Test
    void execute_multiple_pictures() {
        // Arrange
        List<Picture> pictures = new java.util.ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Picture picture = new Picture();
            picture.setTitle("Picture " + i);
            pictures.add(picture);
        }

        when(pictureRepository.findAll()).thenReturn(pictures);

        // Act
        List<Picture> result = listPicturesUseCase.execute();

        // Assert
        assertEquals(5, result.size());
        verify(pictureRepository).findAll();
    }
}

