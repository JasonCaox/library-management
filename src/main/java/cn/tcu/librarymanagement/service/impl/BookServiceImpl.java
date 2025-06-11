package cn.tcu.librarymanagement.service.impl;

import cn.tcu.librarymanagement.entity.Book;
import cn.tcu.librarymanagement.mapper.BookMapper;
import cn.tcu.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void addBook(Book book) {
        bookMapper.add(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookMapper.findAll();
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.update(book);
    }

    @Override
    public Book getBookById(Integer id) {
        return bookMapper.findById(id);
    }

    @Override
    public void deleteBook(Integer id) {
        bookMapper.delete(id);
    }
}
