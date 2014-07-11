package catalog.controllers;


import catalog.daoServices.interfaces.AuthorService;
import catalog.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nickolay
 * Date: 8/12/13
 * Time: 1:00 PM
 */
@RequestMapping("/authors")
@Controller
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    public AuthorController(){}

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel){
        List<Author> authors = (List<Author>) authorService.findAll();
        uiModel.addAttribute("authors", authors);
        return "authors/list";
    }

//    @RequestMapping(method = RequestMethod.GET, params = {"name", "surname"})
//    public String getAuthorByNameAndSurname(@RequestParam("name")String name,
//                                       @RequestParam("surname")String surname, Model model){
//        Author author = authorService.findByNameAndSurname(name,surname);
//        model.addAttribute("author",author);
//        return "/authors/authorBooks";
//    }


//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @Secured("ROLE_ADMIN")
//    @RolesAllowed(value = "ROLE_ADMIN")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method=RequestMethod.GET, params="new")
    public String createAuthor(Model model) {
        model.addAttribute(new Author());
        return "authors/create";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitAuthorFromForm(@Valid Author author, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "authors/create";
        }
        authorService.save(author);
        return "redirect:authors/"+author.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getAuthorById(@PathVariable("id")Long id, Model model){
        Author author = authorService.findById(id);
        model.addAttribute("author",author);
        return "authors/authorBooks";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ="/{id}",params = "edit",method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("author", authorService.findById(id));
        return "authors/update";
    }

    @RequestMapping(value = "/{id}", params = "edit", method = RequestMethod.POST)
    public String update(@Valid Author author, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("author",author);
            return "authors/update";
        }
        authorService.save(author);
        return "redirect:"+author.getId();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id){
        authorService.delete(authorService.findById(id));
        return "redirect:/authors";
    }
}
