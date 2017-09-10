package cn.camork.service.impl;


import cn.camork.mapper.OrderMapper;
import cn.camork.model.Order;
import cn.camork.model.OrderDetail;
import cn.camork.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int addOrder(Order order, List<OrderDetail> orderDetails) {

        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String str = sf.format(d);
        Random r = new Random();
        int i = r.nextInt(100);
        String s = "";
        if (i < 10)
            s = "00" + i;
        else if (i >= 10 && i <= 99)
            s = "0" + i;

        order.setOrderCode(str + s);
        orderMapper.saveOrder(order);
        int orderId = orderMapper.getOrderId(str + s);

        for (OrderDetail od : orderDetails) {
            od.setOrderId(orderId);
            orderMapper.saveOrderDetail(od);
        }

        return orderId;
    }

    @Override
    public void changeOrderStatus(String orderId, int status) {
        Map<String,String> m=new HashMap<>();
        m.put("orderId", orderId);
        orderMapper.updateOrderStatus(orderId,status);
    }

    @Override
    public List<Order> getMyOrders(String username, String status) {
        List<Order> orderList=orderMapper.getMyOrders(username,status);
        return orderList;
    }

    @Override
    public int getOrderStatusById(String orderId) {
        // TODO Auto-generated method stub
        int status=orderMapper.findOrderStatusById(Integer.parseInt(orderId));
        return status;
    }

    @Override
    public void delOrder(int orderId) {
        orderMapper.delOrder(orderId);
    }
}
