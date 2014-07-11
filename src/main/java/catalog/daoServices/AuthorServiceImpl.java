package catalog.daoServices;

import catalog.domain.Author;
import catalog.repositories.AuthorRepository;
import catalog.daoServices.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nickolay
 * Date: 8/11/13
 * Time: 10:27 PM
 */
@Repository
@Service("authorService")
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(readOnly = false)
    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Author author) {
        authorRepository.delete(author);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findOne(id);
    }

    @Override
    public Author findByNameAndSurname(String name, String surname) {
        return authorRepository.findByNameAndSurname(name,surname);
    }

    @Override
    public List<Author> findAll() {
        return (List<Author>) authorRepository.findAll();
    }
}
