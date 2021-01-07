package ziv.pra.relean.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ziv.pra.relean.web.model.Book;
import ziv.pra.relean.web.model.BookRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ziv
 * @date 2020-12-29 11:07 上午
 */
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    /**
     * 查詢所有的資料
     *
     * @return List<Book>
     */
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * 存書籍
     *
     * @param book
     * @return
     */
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    /**
     * 用id取得一個書籍資訊
     * springboot 2.x以上的jpa findOne繼承example了，所以這裡用findById
     *
     * @param id
     * @return
     */
    public Optional<Book> findOne(long id) {
        return bookRepository.findById(id);
    }

    /**
     * 根據id刪除資料
     *
     * @param id
     */
    public void delete(long id) {
        bookRepository.deleteById(id);
    }

    /**
     * 根據作者查詢
     *
     * @param author
     * @return
     */
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    /**
     * 根據作者及狀態查詢
     *
     * @param author
     * @param status
     * @return
     */
    public List<Book> findByAuthorAndStatus(String author, int status) {
        return bookRepository.findByAuthorAndStatus(author, status);
    }

    /**
     * 用描述使用like去查詢
     *
     * @param des
     * @return
     */
    public List<Book> findByDescriptionEndsWith(String des) {
        return bookRepository.findByDescriptionEndsWith(des);
    }

    /**
     * 用等同於 like '%?%' 去查找
     *
     * @param des
     * @return
     */
    public List<Book> findByDescriptionContains(String des) {
        return bookRepository.findByDescriptionContains(des);
    }

    /**
     * 使用自定義的sql去查找
     *
     * @param len
     * @return
     */
    public List<Book> findByMyQuerySql(int len) {
//        return bookRepository.findByMyQuerySql(len);
        return bookRepository.findByMyQuerySql2(len);
    }
}
