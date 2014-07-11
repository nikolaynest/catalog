package catalog.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: nickolay
 * Date: 8/6/13
 * Time: 12:25 AM
 */
@Entity
@Table(name="AUTHOR")
// uniqueConstraints = @UniqueConstraint(name = "NAME_SURNAME_IDX",columnNames = {"NAME", "SURNAME"}))
public class Author implements Serializable{
    private Long id;
    private String name;
    private String surname;
    private Collection<Book> books;

    @Id @Column(name = "AUTHOR_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
//    @Pattern(regexp = "[A-Za-z'А-Яа-я ]",message = "Поле должно содержать имя")
    @Size(min = 1, message = "Поле должно содержать имя")
    @NotEmpty
    @Column(name = "NAME", nullable = false, length = 100)
    public String getName() {
        return name;
    }

//    @Pattern(regexp = "[A-Za-z'А-Яа-я ]", message = "Поле должно содержать фамилию")
    @Size(min = 1, message = "Поле должно содержать фамилию")
    @NotEmpty
    @Column(name = "SURNAME", nullable = false, length = 100)
    public String getSurname() {
        return surname;
    }

    /**
     * TODO: При удалении автора необходимо удалить книгу только в том случае,
     * TODO: если не существует соавторов данной книги
     */
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "BOOK_AUTHOR",
//            joinColumns = @JoinColumn(name = "ID_AUTHOR"),
//            inverseJoinColumns = @JoinColumn(name = "ID_BOOK")
//    )
    public Collection<Book> getBooks() {
        return books;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }
}
