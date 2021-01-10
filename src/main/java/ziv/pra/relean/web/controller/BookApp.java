package ziv.pra.relean.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ziv.pra.relean.web.model.Book;
import ziv.pra.relean.web.model.BookRepository;
import ziv.pra.relean.web.service.BookService;

import java.util.List;
import java.util.Optional;

/**
 * @author ziv
 * @date 2020-12-29 11:09 上午
 */
@RestController
@RequestMapping("/v1")
public class BookApp {
    @Autowired
    private BookService bookService;


    @GetMapping("/books")
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @PostMapping("/books")
    public Book saveBook(@RequestParam String name,
                         @RequestParam String author,
                         @RequestParam String description,
                         @RequestParam int status) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);
        return bookService.save(book);
    }

    /**
     * 直接傳物件
     *
     * @param book
     * @return
     */
    @PostMapping("/books2")
    public Book saveBook2(Book book) {
        return bookService.save(book);
    }

    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable long id) {
        Optional<Book> data = bookService.findOne(id);
        return data.get();
    }

    /**
     * 更新資料
     *
     * @param id
     * @param name
     * @param author
     * @param description
     * @param status
     * @return
     */
    @PutMapping("/books")
    public Book update(@RequestParam long id,
                       @RequestParam String name,
                       @RequestParam String author,
                       @RequestParam String description,
                       @RequestParam int status) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);
        return bookService.save(book);
    }

    /**
     * 刪除一本書
     *
     * @param id
     */
    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable long id) {
        bookService.delete(id);
    }

    /**
     * 用作者搜尋db
     *
     * @param author
     * @return
     */
    @PostMapping("/books/by")
    public List<Book> getByAuthor(@RequestParam String author) {
        return bookService.findByAuthor(author);
    }

    /**
     * 用作者和狀態搜尋db
     *
     * @param author
     * @param status
     * @return
     */
    @PostMapping("/books/by2")
    public List<Book> getByAuthorAndStatus(@RequestParam String author, @RequestParam int status) {
        return bookService.findByAuthorAndStatus(author, status);
    }

    @PostMapping("/books/by3")
    public List<Book> getDescriptionLike(@RequestParam String des) {
        return bookService.findByDescriptionEndsWith(des);
    }

    @PostMapping("/books/by4")
    public List<Book> getDescriptionLikeContain(@RequestParam String des) {
        return bookService.findByDescriptionContains(des);
    }

    @PostMapping("/books/by5")
    public List<Book> getByMyQuerySql(@RequestParam int len) {
        return bookService.findByMyQuerySql(len);
    }

    @PostMapping("/books/by6")
    public int updateBookData(@RequestParam int status, @RequestParam long id) {
        return bookService.updataBookData(status, id);
    }

    @PostMapping("/books/by7")
    public int deleteBookData(@RequestParam long id) {
        return bookService.deleteBookData(id);
    }

    @PostMapping("/books/by8")
    public int testTranscation(@RequestParam long id, @RequestParam int status, @RequestParam long uid) {
        return bookService.testSpringTransaction(id, status, uid);
    }
}
