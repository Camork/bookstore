package cn.camork.crawler;

import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Camork on 2017-05-11.
 * book list Pipeline
 */

@Service("bookListPipeline")
public class BookListPipeline implements Pipeline<BookListSpider> {

    @Override
    public void process(BookListSpider bean) {
        List<String> beanBooks = bean.getBooks();

        for (String book : beanBooks) {
            String[] strs = book.split("/");
            String[] urls=bean.getRequest().getUrl().split("/");

            int bookId=Integer.parseInt(strs[4]);
            String type=urls[urls.length - 1];

            String url="https://api.douban.com/v2/book/"+bookId+"?"+"bookType="+type;
            DeriveSchedulerContext.into(bean.getRequest().subRequest(url));
        }
    }
}
