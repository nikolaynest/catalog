package catalog.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: nickolay
 * Date: 8/6/13
 * Time: 12:19 AM
 */
@Entity
@Table(name = "BOOK")
@NamedNativeQueries({
        @NamedNativeQuery(name = "Book.getBookByAuthor",
                query = " select * from(BOOK INNER JOIN BOOK_AUTHOR ON BOOK.BOOK_ID=BOOK_AUTHOR.ID_BOOK)" +
                        "INNER JOIN AUTHOR ON AUTHOR_ID = BOOK_AUTHOR.ID_AUTHOR" +
                        "WHERE AUTHOR.NAME = ? AND AUTHOR.SURNAME = ?",resultClass = Book.class)
})
public class Book implements Serializable{


    private Long id;
    private String bookTitle;
    private String description;
    private String publicationDate;
    private Collection<Author> authors;
    private byte[] image;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="BOOK_ID")
    public Long getId() {
        return id;
    }

    @Size(min = 1, message = "Это поле обязательно для заполнения")
    @Column(name = "BOOK_TITLE",nullable = false)
    public String getBookTitle() {
        return bookTitle;
    }

    @Size(min = 1, message = "Это поле обязательно для заполнения")
    @Column(name = "DESCRIPTION",length = 2000, nullable = false)
    public String getDescription() {
        return description;
    }

    @Size(max = 4, min = 4, message = "Invalid year of publication")
//    @Pattern(regexp = "[0-9]")
    @Column(name = "PUBLICATION_DATE", nullable = false)
    public String getPublicationDate() {
        return publicationDate;
    }


    @NotNull(message = "Книгу кто-то же написал, выберите автора(ов)!")
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "BOOK_AUTHOR",
            joinColumns = @JoinColumn(name = "ID_BOOK"),
            inverseJoinColumns = @JoinColumn(name = "ID_AUTHOR")
    )
    public Collection<Author> getAuthors() {
        return authors;
    }

    @Basic(fetch = FetchType.LAZY)
    @Lob @Column(name = "IMAGE", nullable = true)
    public byte[] getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setAuthors(Collection<Author> authors) {
        this.authors = authors;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
