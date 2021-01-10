package ziv.pra.relean.web.controller;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/book")
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
}
