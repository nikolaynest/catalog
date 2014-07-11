package catalog.daoServices.interfaces;

import catalog.domain.Author;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nickolay
 * Date: 8/11/13
 * Time: 10:17 PM
 */
public interface AuthorService {

    void save(Author author);


    void delete(Author author);

    Author findById(Long id);

    Author findByNameAndSurname(String name, String surname);

    List<Author> findAll();
}
