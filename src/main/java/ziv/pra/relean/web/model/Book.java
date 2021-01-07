package ziv.pra.relean.web.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author ziv
 * @date 2020-12-28 8:21 下午
 */
//因為springboot 2.2之後有更改
//@Component
//@ConfigurationProperties(prefix = "book")
@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int status;
    private String name;
    private String author;
    private String description;
}
