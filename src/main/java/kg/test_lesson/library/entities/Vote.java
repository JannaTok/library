package kg.test_lesson.library.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books bookId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;
}
