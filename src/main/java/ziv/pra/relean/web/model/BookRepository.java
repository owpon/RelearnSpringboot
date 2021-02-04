package ziv.pra.relean.web.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 要符合jpa撰寫的規則撰寫
 * https://docs.spring.io/spring-data/jpa/docs/2.4.2/reference/html/#jpa.query-methods.query-creation
 *
 * @author ziv
 * @date 2020-12-29 10:39 上午
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    //分頁查詢
    Page<Book> findAll(Pageable pageable);

    //同 select * from books where author =?
    List<Book> findByAuthor(String author);

    //同 select * from books where author = ? and status = ?
    List<Book> findByAuthorAndStatus(String author, int status);

    //同 select * from books where description like '%?'
    List<Book> findByDescriptionEndsWith(String des);

    //同 select * from books where description like '%?%'
    List<Book> findByDescriptionContains(String des);

    //自定義的查詢(select * from books where length(`name`)>3
    //這裡的Book需要對應到我們自己定義的Book.java 而非db的名稱
    //後面問號的數字是搭配傳入的參數做傳入，如果我們有兩個參數都要傳進去就會變成?1 and b.author =?2 這種類型
    @Query("select b from Book b where length(b.name) >?1 ")
    List<Book> findByMyQuerySql(int len);

    //這裡直接使用sql語句，但是要搭配參數"nativeQuery"才可以使用
    @Query(value = "select * from book where length(name)>?1", nativeQuery = true)
    List<Book> findByMyQuerySql2(int len);

    //如果是用update語句，就要使用modifying的annotation去更改
    //因為這是update語句，所以要再service層加入transaction
    @Modifying
    @Query("update Book b set b.status=?1 where b.id=?2")
    int updateMyData(int status, long id);

    @Modifying
    @Query("delete from Book b where b.id=?1 ")
    int deleteMyData(long id);

}
