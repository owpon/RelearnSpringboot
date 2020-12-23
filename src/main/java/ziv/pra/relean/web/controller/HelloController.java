package ziv.pra.relean.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ziv
 * @date 2020-12-23 11:48 上午
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello world";
    }

}
