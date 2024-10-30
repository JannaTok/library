package kg.test_lesson.library.services.impl;

import kg.test_lesson.library.dto.ImageDto;
import kg.test_lesson.library.dto.ImageResponseDto;
import kg.test_lesson.library.entities.Books;
import kg.test_lesson.library.repository.BookRepository;
import kg.test_lesson.library.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public byte[] getBookById(Long id) {
        return bookRepository.findBook(id);
    }

    @Override
    public void createBook(Books book) {
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, Books book) {

    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<ImageResponseDto> getImage() {
        List<ImageDto> imageDtos = bookRepository.findAllImageBytesAndName();
        return imageDtos.stream().map(imageDto -> new ImageResponseDto(imageDto.getId(),
                Base64.getEncoder().encodeToString(imageDto.getImageBytes()),
                imageDto.getName())).collect(Collectors.toList());
    }
}
