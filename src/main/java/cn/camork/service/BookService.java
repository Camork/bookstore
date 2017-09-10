package cn.camork.service;


import cn.camork.crawler.Book;
import cn.camork.model.BookBean;
import cn.camork.model.BookType;

import java.util.List;

public interface BookService {

    int insertBookType(BookType bookType);

    int insertBook(Book book);

    List<Book> getHotBooks();

    List<BookType> getBookTypes();

    List<BookBean> getBooksByType(String typeName);

}
