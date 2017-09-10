package cn.camork.service.impl;


import cn.camork.mapper.BookMapper;
import cn.camork.crawler.Book;
import cn.camork.model.BookBean;
import cn.camork.model.BookType;
import cn.camork.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public int insertBookType(BookType bookType) {
        return bookMapper.insertBookType(bookType);
    }

    @Override
    public int insertBook(Book book) {
        return bookMapper.insertBook(book);
    }

    @Override
    public List<Book> getHotBooks() {
        return bookMapper.getHotBooks();
    }

    @Override
    public List<BookType> getBookTypes() {
        return bookMapper.getBookTypes();
    }

    @Override
    public List<BookBean> getBooksByType(String typeName) {
        return bookMapper.getBooksByType(typeName);
    }


}
