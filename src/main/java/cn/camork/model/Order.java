package cn.camork.model;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private String userName;
    private String orderCode;
    private int orderStatus;
    private Date orderDate;
    private float totalAmount;
    private int totalNumber;
    private List<OrderDetail> odetails;

    public Order() {
    }

    public Order(String userName, int orderStatus, Date orderDate, float totalAmount, int totalNumber) {
        this.userName = userName;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.totalNumber = totalNumber;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<OrderDetail> getOdetails() {
        return odetails;
    }

    public void setOdetails(List<OrderDetail> odetails) {
        this.odetails = odetails;
    }
}
