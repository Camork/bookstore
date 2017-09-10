package cn.camork.crawler;

import cn.camork.service.BookService;
import com.geccocrawler.gecco.pipeline.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Camork on 2017-05-12.
 * BookPipeline
 */

@Service("bookPipeline")
public class BookPipeline implements Pipeline<Book> {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void process(Book bean) {
        try {
            String tempDate = bean.getTempDate();
            SimpleDateFormat format = new SimpleDateFormat();
            if (tempDate.length() == 4) {
                format = new SimpleDateFormat("yyyy");
            } else {
                String[] strs = tempDate.split("-");
                if (strs.length == 2) {
                    format = new SimpleDateFormat("yyyy-MM");
                } else if (strs.length == 3) {
                    format = new SimpleDateFormat("yyyy-MM-dd");
                }
            }

            bean.setPubDate(format.parse(tempDate));
        } catch (ParseException e) {
            bean.setPubDate(new Date());
        }
        bean.setBookDescribe(bean.getBookDescribe().replace("\n", "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"));
        String tempPrice=bean.getTempPrice();
        if(tempPrice.equals("")){
            bean.setBookPrice(50.0f);
        }else{
            bean.setBookPrice(Float.parseFloat(tempPrice.replaceAll("[^.\\d]", "")));
        }
        bookService.insertBook(bean);
    }
}
