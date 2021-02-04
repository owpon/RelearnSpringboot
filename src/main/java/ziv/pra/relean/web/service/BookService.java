package ziv.pra.relean.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ziv.pra.relean.web.model.Book;
import ziv.pra.relean.web.model.BookRepository;

import javax.transaction.Transactional;
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

    public Page<Book> findAllBypage(Pageable pages) {
        //依照id排序
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        //因為用new 的方法已經被protect起來了，所以換成PageRequest.of
//        Pageable pages = PageRequest.of(1, 5, sort);
        return bookRepository.findAll(pages);
    }

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

    //要再service層加入transactional來確保如果出錯可以rollback回去
    @Transactional
    public int updataBookData(int status, long id) {
        return bookRepository.updateMyData(status, id);
    }

    @Transactional
    public int deleteBookData(long id) {
        return bookRepository.deleteMyData(id);
    }

    //這裡使用spring 的transactional
    @org.springframework.transaction.annotation.Transactional
    public int testSpringTransaction(long id, int status, long uid) {
        int dcount = bookRepository.deleteMyData(id);
        int ucount = bookRepository.updateMyData(status, uid);
        return dcount + ucount;
    }
}

