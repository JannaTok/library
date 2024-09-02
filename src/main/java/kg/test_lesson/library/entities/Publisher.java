package kg.test_lesson.library.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
