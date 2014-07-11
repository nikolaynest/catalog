package catalog.daoServices;

import catalog.domain.Book;
import catalog.repositories.BookRepository;
import catalog.daoServices.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nickolay
 * Date: 8/6/13
 * Time: 11:27 AM
 */
@Service("bookService")
@Repository     //todo; read about what does it's mean = propagation.supports?
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

//    private SessionFactory sessionFactory;
//
//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//    @Resource(name = "sessionFactory")
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Transactional(readOnly = false)
    @Override
    public void save(Book book){
        bookRepository.save(book);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Book findById(Long id){
        return bookRepository.findOne(id);
    }

    @Override
    public List<Book> findByBookTitleContaining(String bookTitle) {
        return bookRepository.findByBookTitleContaining(bookTitle);
    }

    @Override
    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

//    @Override
//    public List<Book> findByAuthor(String name, String surname) {
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.getNamedQuery("Book.getBookByAuthor");
//        query.setString(0, name);
//        query.setString(1, surname);
//        return (List<Book>)query.list();
//
//    }

}


//    private SessionFactory sessionFactory;
//    private DataSource dataSource;

//    @Autowired
//    public BookDaoImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory){
//        this.sessionFactory = sessionFactory;
//    }
//    private Session currentSession(){
//        return sessionFactory.getCurrentSession();
//    }
//
//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//    @Resource(name = "sessionFactory")
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Resource(name = "dataSource")
//    public void setDataSource(DataSource dataSource){
//        this.dataSource = dataSource;
//    }
//    public DataSource getDataSource() {
//        return dataSource;
//    }