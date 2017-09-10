package cn.camork.model;

public class OrderDetail {
	private int odetailId;
	private int orderId;
	private int bookId;
	private float orderPrice;
	private String bookName;
	private int bookNum;
	private String bookPic;

    public OrderDetail() {
    }

    public OrderDetail(int bookId, float orderPrice, String bookName, int bookNum,String bookPic) {
        this.bookId = bookId;
        this.orderPrice = orderPrice;
        this.bookName = bookName;
        this.bookNum = bookNum;
        this.bookPic = bookPic;
    }

    public String getBookPic() {
        return bookPic;
    }

    public void setBookPic(String bookPic) {
        this.bookPic = bookPic;
    }

    public int getOdetailId() {
        return odetailId;
    }

    public void setOdetailId(int odetailId) {
        this.odetailId = odetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }
}
