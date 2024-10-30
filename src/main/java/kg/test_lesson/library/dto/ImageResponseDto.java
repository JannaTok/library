package kg.test_lesson.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageResponseDto {

    Long id;
    String image;
    String name;
}
