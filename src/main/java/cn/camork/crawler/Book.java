package cn.camork.crawler;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.JSONPath;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.spider.JsonBean;

import java.util.Date;

/**
 * Created by Camork on 2017-05-11.
 * book detail Json info
 */

@Gecco(matchUrl = "https://api.douban.com/v2/book/{bookId}?bookType={typeName}", pipelines = {"consolePipeline", "bookPipeline"})
public class Book implements JsonBean {

    private static final long serialVersionUID = 5107858315835165474L;

    @RequestParameter
    private int bookId;

    @RequestParameter
    private String typeName;

    @JSONPath("$.title")
    private String bookName;

    private Float bookPrice;

    @JSONPath("$.price")
    private String tempPrice;

    @JSONPath("$.author[0]")
    private String bookAuthor;

    @JSONPath("$.publisher")
    private String publisher;

    @JSONPath("$.images.large")
    private String bookPic;

    @JSONPath("$.pubdate")
    private String tempDate;

    private Date pubDate;

    @JSONPath("$.summary")
    private String bookDescribe;

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

    public String getTempPrice() {
        return tempPrice;
    }

    public void setTempPrice(String tempPrice) {
        this.tempPrice = tempPrice;
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

    public String getTempDate() {
        return tempDate;
    }

    public void setTempDate(String tempDate) {
        this.tempDate = tempDate;
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
}
