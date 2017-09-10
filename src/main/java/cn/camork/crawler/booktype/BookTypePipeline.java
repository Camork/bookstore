package cn.camork.crawler.booktype;

import cn.camork.model.BookType;
import cn.camork.service.BookService;
import com.geccocrawler.gecco.pipeline.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookTypePipeline")
public class BookTypePipeline implements Pipeline<BookTypeSpider> {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

	@Override
	public void process(BookTypeSpider bean) {
         List<Category>  categories=bean.getCategories();

        for(Category category : categories) {

            for (BookType bookType :category.getBookTypes()){

                bookType.setTypeTitle(category.getTagTitle());
                bookService.insertBookType(bookType);
            }



        }

	}



}