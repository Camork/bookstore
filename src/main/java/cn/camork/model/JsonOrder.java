package cn.camork.model;

import java.util.List;

/**
 * Created by Camork on 2017-06-06.
 */
public class JsonOrder {

    private int totalNumber;
    private int totalAmount;
    private List<GoodsListBean> goodsList;

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<GoodsListBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsListBean> goodsList) {
        this.goodsList = goodsList;
    }

    public static class GoodsListBean {

        private int bookId;
        private String bookPic;
        private String bookName;
        private String bookAuthor;
        private String publisher;
        private int num;
        private int bookPrice;

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public String getBookPic() {
            return bookPic;
        }

        public void setBookPic(String bookPic) {
            this.bookPic = bookPic;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getBookAuthor() {
            return bookAuthor;
        }

        public void setBookAuthor(String bookAuthor) {
            this.bookAuthor = bookAuthor;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getBookPrice() {
            return bookPrice;
        }

        public void setBookPrice(int bookPrice) {
            this.bookPrice = bookPrice;
        }
    }
}
