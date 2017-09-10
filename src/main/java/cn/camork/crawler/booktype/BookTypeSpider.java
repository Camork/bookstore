package cn.camork.crawler.booktype;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * Created by Camork on 2017-05-05.
 */

@Gecco(matchUrl = "https://book.douban.com/tag/", pipelines = {"consolePipeline", "bookTypePipeline"})
public class BookTypeSpider implements HtmlBean {

    private static final long serialVersionUID = 4679589873307460519L;

    @HtmlField(cssPath = ".article > div:nth-child(2) > div")
    private List<Category>  categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
