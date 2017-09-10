package cn.camork.crawler.booktype;

import cn.camork.model.BookType;
import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * Created by Camork on 2017-05-21.
 */
public class Category implements HtmlBean {
    private static final long serialVersionUID = 7344926081913098617L;

    @Text
    @Attr("name")
    @HtmlField(cssPath = "a > h2")
    private String tagTitle;

    @HtmlField(cssPath = "table tr >td")
    private List<BookType> bookTypes;

    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public List<BookType> getBookTypes() {
        return bookTypes;
    }

    public void setBookTypes(List<BookType> bookTypes) {
        this.bookTypes = bookTypes;
    }
}
