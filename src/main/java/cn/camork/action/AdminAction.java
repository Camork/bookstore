package cn.camork.action;

import cn.camork.model.Order;
import cn.camork.service.OrderService;
import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.geccocrawler.gecco.request.HttpGetRequest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Camork on 2017-05-26.
 */
@Controller
@RequestMapping("/admin")
public class AdminAction {

    @Autowired
    private PipelineFactory springPipelineFactory;

    @Autowired
    private OrderService orderService;

    @RequiresRoles("admin")
    @RequestMapping("adminCenter")
    public String adminCenter(){
        return "/admin/adminCenter";
    }

    @RequiresRoles("admin")
    @RequestMapping("orderList")
    public String adminCenter(@RequestParam(required = false) String status, Map<String, List<Order>> m) {

        List<Order> orders = orderService.getMyOrders(null,status);
        m.put("orders", orders);
        return "/admin/orderList";
    }

    @ResponseBody
    @RequestMapping("/updateType")
    public Map<String, String> updateType() {
        Map<String, String> m = new HashMap<>();

        HttpGetRequest start = new HttpGetRequest("https://book.douban.com/tag");

        try {
            GeccoEngine.create()
                    .classpath("cn.camork.crawler.booktype")
                    .pipelineFactory(springPipelineFactory)
                    .start(start)
                    .run();
            m.put("state", "ok");

        } catch (Exception e) {
            e.printStackTrace();
            m.put("state", "fail");
        }
        return m;
    }

    @ResponseBody
    @RequestMapping("/updateBookByType")
    public Map<String, String> updateBookByType(String type) {
        Map<String, String> m = new HashMap<>();

        if ("".equals(type)) {
            m.put("state", "fail");
        } else {
            HttpGetRequest start = new HttpGetRequest("https://book.douban.com/tag/" + type);
            GeccoEngine.create()
                    .classpath("cn.camork.crawler")
                    .pipelineFactory(springPipelineFactory)
                    .thread(3)
                    .start(start)
                    .run();
            m.put("state", "ok");

        }
        return m;
    }

    @ResponseBody
    @RequestMapping("/updateNewBook")
    public Map<String, String> updateNewBook() {

        Map<String, String> m = new HashMap<>();

        HttpGetRequest start = new HttpGetRequest("https://book.douban.com/");

        try {
            GeccoEngine.create()
                    .classpath("cn.camork.crawler")
                    .pipelineFactory(springPipelineFactory)
                    .thread(3)
                    .start(start)
                    .run();
            m.put("state", "ok");

        } catch (Exception e) {
            e.printStackTrace();
            m.put("state", "fail");
        }
        return m;
    }

}
