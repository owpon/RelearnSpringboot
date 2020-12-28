package ziv.pra.relean.web.controller;

import org.springframework.web.bind.annotation.*;
import ziv.pra.relean.web.model.TestModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ziv
 * @date 2020-12-23 11:48 上午
 */
//模板開發用controller，rest風格的用restController
//@Controller
@RestController
@RequestMapping("/v1")
public class HelloController {

    //RequestMapping不寫method的話，就是get/post/delete 都可以請求到
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "hello world";
    }

    //使用ResponseBody可以直接返回books字串而不是模板
    @GetMapping("/books")
    @ResponseBody
    public String getAll() {
        return "books";
    }

    //冒號後面可以接上正則表達式
    //PathVariable是在url後面接一堆東西
    @GetMapping("/books/{id:[0-9]+}")
    public Map<String, String> getBooks(@PathVariable("id") int bid) {
        System.out.println("---id:" + bid);
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("hello", "world");
        reqMap.put("world", "qq");
        return reqMap;

    }


    //回想起怎麼用model object傳值
    @PostMapping(value = "/addManyBook", consumes = "application/json", produces = "application/json")
    public TestModel addManyboks(@RequestBody TestModel inputModel) {
        return inputModel;
    }

}
