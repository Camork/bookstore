package cn.camork.model;

import java.util.Date;

/**
 * Created by Camork on 2017-05-11.
 * book detail Json info
 */

public class BookBean {

    private static final long serialVersionUID = 5107858315835165474L;

    private int bookId;

    private String typeName;

    private String bookName;

    private Float bookPrice;

    private String bookAuthor;

    private String publisher;

    private String bookPic;

    private Date pubDate;

    private String bookDescribe;

    public BookBean() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Float bookPrice) {
        this.bookPrice = bookPrice;
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

    public String getBookPic() {
        return bookPic;
    }

    public void setBookPic(String bookPic) {
        this.bookPic = bookPic;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getBookDescribe() {
        return bookDescribe;
    }

    public void setBookDescribe(String bookDescribe) {
        this.bookDescribe = bookDescribe;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "bookId=" + bookId +
                ", typeName='" + typeName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookPrice='" + bookPrice + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", publisher='" + publisher + '\'' +
                ", bookPic='" + bookPic + '\'' +
                ", pubDate=" + pubDate +
                ", bookDescribe='" + bookDescribe + '\'' +
                '}';
    }
}
