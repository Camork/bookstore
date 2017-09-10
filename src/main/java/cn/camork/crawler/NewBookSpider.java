package cn.camork.crawler;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * Created by Camork on 2017-05-14.
 * 热门图书
 */
@Gecco(matchUrl = "https://book.douban.com/", pipelines = {"consolePipeline","newBookPipeline"})
public class NewBookSpider implements HtmlBean {

    private static final long serialVersionUID = 7568012105851744604L;

    @Request
    private HttpRequest request;

    @Href
    @HtmlField(cssPath=".carousel .cover>a")
    private List<String> books;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }
}
