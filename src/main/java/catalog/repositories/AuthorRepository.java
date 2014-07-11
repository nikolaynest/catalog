package catalog.repositories;

import catalog.domain.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: nickolay
 * Date: 8/11/13
 * Time: 10:28 PM
 */
public interface AuthorRepository extends CrudRepository<Author,Long> {

    Author findByNameAndSurname(String name, String surname);

}
