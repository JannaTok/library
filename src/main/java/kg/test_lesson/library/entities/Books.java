package kg.test_lesson.library.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    //@Lob
    @Column(name = "content", columnDefinition = "BYTEA")
    private byte[] content;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "isbn")
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genres genreId;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author authorId;

    @Column(name = "publish_year")
    private Integer publishYear;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisherId;

    //@Lob
    @Column(name = "image_bytes", columnDefinition = "BYTEA")
    private byte[] imageBytes;

    @Column(name = "avg_rating")
    private Double avgRating;

    @Column(name = "total_vote_count")
    private Integer totalVoteCount;

    @Column(name = "total_rating")
    private Integer totalRating;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "description")
    private String description;

}
