package kg.test_lesson.library.dto;

import kg.test_lesson.library.entities.Author;
import kg.test_lesson.library.entities.Genres;
import kg.test_lesson.library.entities.Publisher;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BookCreateDTO {
    private String name;
    private MultipartFile content;
    private Integer pageCount;
    private String isbn;
    private Genres genreId;
    private Author authorId;
    private Integer publishYear;
    private Publisher publisherId;
    private MultipartFile imageBytes;
    private Double avgRating;
    private Integer totalVoteCount;
    private Integer totalRating;
    private Integer viewCount;
    private String description;
}
