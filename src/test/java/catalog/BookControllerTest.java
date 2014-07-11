package catalog;

import catalog.controllers.BookController;
import catalog.daoServices.interfaces.AuthorService;
import catalog.domain.Book;
import catalog.daoServices.interfaces.BookService;
import org.junit.Test;
import org.springframework.ui.Model;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * Created with IntelliJ IDEA.
 * User: nickolay
 * Date: 8/10/13
 * Time: 12:45 PM
 */
public class BookControllerTest {
    @Test
    public void sizeOfBooksTest(){
        BookService bookService = mock(BookService.class);
        List<Book> expectedBooks = asList(new Book(), new Book(), new Book());
        when(bookService.findAll()).thenReturn(expectedBooks);
        assertEquals(bookService.findAll().size(),3);
    }

    @Test
    public void viewTest(){
        BookService bookService = mock(BookService.class);
        Model mockUiModel = mock(Model.class);

        AuthorService authorService = mock(AuthorService.class);


        BookController bookController = new BookController(bookService, authorService);
        String expectedViewName = bookController.list(mockUiModel);
        assertEquals("books/list", expectedViewName);
    }

}
