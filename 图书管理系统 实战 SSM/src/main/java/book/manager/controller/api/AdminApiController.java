package book.manager.controller.api;

import book.manager.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/*注解标在类上表示类中的所有响应请求的方法都是以该地址作为父路径。*/
@Controller
@RequestMapping("/api/admin")
public class AdminApiController {

    /*
     * get请求：从指定的资源请求数据
     * post请求：向指定的资源提交要被处理的数据
     * */

    @Resource
    BookService service;

    /*
    * @RequestParam:把请求中的指定名称的参数传递给控制器中的形参赋值
    * */
    @RequestMapping(value = "/del-book", method = RequestMethod.GET)
    public String deleteBook(@RequestParam("id") int id){
        service.deleteBook(id);
        return "redirect:/page/admin/book";
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String addBook(@RequestParam("title") String title,
                          @RequestParam("desc") String desc,
                          @RequestParam("price") double price){
        service.addBook(title, desc, price);
        return "redirect:/page/admin/book";
    }
}
