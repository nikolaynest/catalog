package catalog.controllers;

import catalog.daoServices.interfaces.AuthorService;
import catalog.daoServices.interfaces.BookService;
import catalog.domain.Author;
import catalog.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nickolay
 * Date: 8/8/13
 * Time: 11:37 PM
 */
@RequestMapping("/books")
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    public BookController() {
    }

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {

        List<Book> books = bookService.findAll();
        uiModel.addAttribute("books", books);

        return "books/list";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchByTitle(@RequestParam String bookTitle, Model model){
        List<Book> books = bookService.findByBookTitleContaining(bookTitle);
        model.addAttribute("books", books);
        return "books/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showById(@PathVariable("id") Long id, Model uiModel) {
        Book book = bookService.findById(id);
        uiModel.addAttribute("book", book);
        return "books/showOne";
    }

//    @RequestMapping(value = "{bookTitle}", method = RequestMethod.GET)
//    public String showByName(@PathVariable("bookTitle")String bookTitle, Model uiModel){
//        Book book = (Book) bookService.findByBookTitle(bookTitle);
//        uiModel.addAttribute("book", book);
//        return "books/showByTitle";
//    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET, params = "new")
    public String fillFormForCreatingBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        return "books/create";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createBookFromForm(@Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/create";
        }

        bookService.save(book);

        return "redirect:books/" + book.getId();
    }

    class AuthorEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) {
            Long id = Long.parseLong(text);
            Author author = authorService.findById(id);
            setValue(author);
        }
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Author.class, new AuthorEditor());
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ="/{id}",params = "edit",method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model){
        Book book = bookService.findById(id);
        List<Author> authors = authorService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        return "books/update";
    }

    @RequestMapping(value = "/{id}",params = "edit", method = RequestMethod.POST)
    public String update(@Valid Book book, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("book",book);
            return "books/update";
        }
        bookService.save(book);
        return "redirect:"+book.getId();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id){
        bookService.delete(bookService.findById(id));
        return "redirect:/books";
    }

}
