package catalog.repositories;

import catalog.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nickolay
 * Date: 8/7/13
 * Time: 4:10 PM
 */

public interface BookRepository extends CrudRepository<Book, Long> {


    List<Book> findByBookTitleContaining(String bookTitle);

}
