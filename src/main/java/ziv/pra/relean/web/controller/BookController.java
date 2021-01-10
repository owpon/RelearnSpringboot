package ziv.pra.relean.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ziv.pra.relean.web.model.Book;
import ziv.pra.relean.web.service.BookService;

import java.util.List;
import java.util.Optional;

/**
 * @author ziv
 * @date 2021-01-07 11:23 上午
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 返回列表
     *
     * @param model
     * @return
     */
    @GetMapping("/books")
    public String list(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
//        這是返回books.html的頁面
        return "books";
    }

    /**
     * 取的詳情
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/book/{id}")
    public String bookDetail(@PathVariable long id, Model model) {
        Optional<Book> opBook = bookService.findOne(id);
        if (opBook.isEmpty()) {
            model.addAttribute("book", new Book());
        } else {
            model.addAttribute("book", opBook.get());

        }
        return "book";
    }

    /**
     * 跳轉input頁面
     *
     * @return
     */
    @GetMapping("/book/input")
    public String pageInput() {
        return "input";
    }

    /**
     * 送出新書單
     *
     * @param book
     * @return
     */
    @PostMapping("/books")
    public String postSumit(Book book) {
        bookService.save(book);
        return "redirect:/books";
    }
}
