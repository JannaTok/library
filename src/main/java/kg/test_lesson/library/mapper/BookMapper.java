package kg.test_lesson.library.mapper;

import kg.test_lesson.library.dto.BookCreateDTO;
import kg.test_lesson.library.entities.Books;
import lombok.Data;

import java.io.IOException;

@Data
public class BookMapper {
    public static Books getModel(BookCreateDTO dto) throws IOException {
        Books books = new Books();
        books.setName(dto.getName());

        if (dto.getContent() != null && !dto.getContent().isEmpty()) {
            books.setContent(dto.getContent().getBytes());
        }
        books.setPageCount(dto.getPageCount());
        books.setIsbn(dto.getIsbn());
        books.setGenreId(dto.getGenreId());
        books.setAuthorId(dto.getAuthorId());
        books.setPublishYear(dto.getPublishYear());
        books.setPublisherId(dto.getPublisherId());

        if (dto.getImageBytes() != null && !dto.getImageBytes().isEmpty()) {
            books.setImageBytes(dto.getImageBytes().getBytes());
        }
        books.setAvgRating(dto.getAvgRating());
        books.setTotalVoteCount(dto.getTotalVoteCount());
        books.setTotalRating(dto.getTotalRating());
        books.setViewCount(dto.getViewCount());
        books.setDescription(dto.getDescription());

        return books;
    }
}
