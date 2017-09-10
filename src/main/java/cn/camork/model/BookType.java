package cn.camork.model;

import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;


public class BookType implements HtmlBean {

    private static final long serialVersionUID = 5164188167576563654L;

    @Text
    @HtmlField(cssPath = "a")
    private String typeName;

    private String typeTitle;

    @Href
    @HtmlField(cssPath = "a")
    private String url;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "typeName='" + typeName + '\'' +
                ", typeTitle='" + typeTitle + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
