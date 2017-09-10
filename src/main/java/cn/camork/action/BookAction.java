package cn.camork.action;

import cn.camork.model.BookBean;
import cn.camork.model.BookType;
import cn.camork.service.BookService;
import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.geccocrawler.gecco.request.HttpGetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Camork on 2017-05-13.
 */
@Controller
@RequestMapping("/book")
public class BookAction {

    @Autowired
    private PipelineFactory springPipelineFactory;

    @Autowired
    private BookService bookService;

    @RequestMapping("/category")
    public String getCategory(Map<String, Map<String, List<BookType>>> m) {

        Map<String, List<BookType>> categories = new HashMap<>();
        List<BookType> lists = bookService.getBookTypes();

        List<BookType> data = new ArrayList<>();
        for (BookType bookType : lists) {

            String title = bookType.getTypeTitle();

            if (categories.get(title) == null) {
                data = new ArrayList<>();
                data.add(bookType);
            } else {
                categories.get(title).add(bookType);
            }
            categories.put(title, data);


        }
        m.put("categories", categories);
        return "page/category";
    }

    @RequestMapping("/type/{typeName}")
    public String tag(@PathVariable String typeName, Map<String,List<BookBean>> m, Model model) {
        Index.log.warn(typeName);
        model.addAttribute("typeName",typeName);
        if (bookService.getBooksByType(typeName).isEmpty()) {

            HttpGetRequest start = new HttpGetRequest("https://book.douban.com/tag/" + typeName);
            GeccoEngine.create()
                    .classpath("cn.camork.crawler")
                    .pipelineFactory(springPipelineFactory)
                    .start(start)
                    .run();
        }

        List<BookBean> lists=bookService.getBooksByType(typeName);
        m.put("lists",lists);
        return "page/typeList";
    }

}
