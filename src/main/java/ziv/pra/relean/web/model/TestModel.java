package ziv.pra.relean.web.model;

import lombok.Data;
import org.springframework.stereotype.Repository;

/**
 * @author ziv
 * @date 2020-12-28 10:15 上午
 */
@Data
@Repository
public class TestModel {
    private String name;
    private String author;
    private String isbn;
}
