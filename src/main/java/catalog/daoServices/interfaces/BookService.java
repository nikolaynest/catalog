package catalog.daoServices.interfaces;

import catalog.domain.Book;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nickolay
 * Date: 8/6/13
 * Time: 12:04 PM
 */
public interface BookService {

    void save(Book book);

    void delete(Book book);

    Book findById(Long id);

    List<Book> findByBookTitleContaining(String bookTitle);

    List<Book> findAll();

//    List findByAuthor(String name, String surname);

}
