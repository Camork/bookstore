package cn.camork.action;

import cn.camork.model.JsonOrder;
import cn.camork.model.Order;
import cn.camork.model.OrderDetail;
import cn.camork.service.OrderService;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Camork on 2017-06-06.
 */

@Controller
@RequestMapping("/order")
public class OrderAction {

    @Autowired
    private OrderService orderService;

    @ResponseBody
    @RequestMapping("/addOrder")
    public Map<String, String> addOrder(@RequestParam String json) {

        Map<String, String> m = new HashMap<>();

        JsonOrder jsonOrder = JSON.parseObject(json, JsonOrder.class);

        String principal = (String) SecurityUtils.getSubject().getPrincipal();

        if (principal == null || "".equals(principal)) {
            m.put("message", "请先登录");
        } else {
            Order order = new Order(principal, 0, new Date(), jsonOrder.getTotalAmount(), jsonOrder.getTotalNumber());

            List<OrderDetail> orderDetails = new ArrayList<>();

            List<JsonOrder.GoodsListBean> goodsList = jsonOrder.getGoodsList();
            for (int j = 0; j < jsonOrder.getGoodsList().size(); j++) {
                JsonOrder.GoodsListBean goodsListBean = goodsList.get(j);
                OrderDetail od = new OrderDetail(goodsListBean.getBookId(), goodsListBean.getBookPrice(), goodsListBean.getBookName(), goodsListBean.getNum(), goodsListBean.getBookPic());
                orderDetails.add(od);
            }

            orderService.addOrder(order, orderDetails);
            m.put("message", "提交成功");
        }
        return m;
    }

    @RequestMapping("/getMyOrders")
    public String getMyOrders(@RequestParam(required = false) String status, Map<String, List<Order>> m) {
        List<Order> orders = orderService.getMyOrders((String) SecurityUtils.getSubject().getPrincipal(), status);
        m.put("orders", orders);
        return "/user/userCenter";
    }

    @ResponseBody
    @RequestMapping("/handleOrderStatus")
    public Map<String, String> handleOrderStatus(@RequestParam String orderId, @RequestParam int status) {
        Map<String, String> m = new HashMap<>();
        try {
            orderService.changeOrderStatus(orderId, status);
            m.put("handle", "success");
        } catch (Exception e) {
            e.printStackTrace();
            m.put("handle", "exception");
        }
        return m;
    }

    @RequestMapping("/delOrder")
    public String delOrder(int orderId) {
        orderService.delOrder(orderId);
        return "/user/userCenter";
    }

}
