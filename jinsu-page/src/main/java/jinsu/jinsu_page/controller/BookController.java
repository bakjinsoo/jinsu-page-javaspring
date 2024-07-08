package jinsu.jinsu_page.controller;

import jinsu.jinsu_page.domain.Book;
import jinsu.jinsu_page.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@Controller
public class BookController {
    private BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String bookList(Model model) {
        List<Book> books=bookService.findBooks();
        model.addAttribute("books", books);
        return "books/bookList";
    }
    @GetMapping("/members/library/loan")
    public String libraryLoan(@RequestParam("count") int count, @RequestParam("title") String title, Model messageModel, Model booksModel) {
        if(count > 0) {
            Book book = bookService.findBookByName(title);
            book.setCount(book.getCount() - 1);
            bookService.saveBook(book);
            messageModel.addAttribute("message", book.getTitle() + "책을 대출했습니다.");
        } else {
            messageModel.addAttribute("message", "책을 대출할 수 없습니다.");
        }
        List<Book> books = bookService.findBooks();
        booksModel.addAttribute("books", books);
        return "books/bookList";
    }


}
