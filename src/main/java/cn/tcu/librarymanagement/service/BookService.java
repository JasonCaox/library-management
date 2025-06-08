package cn.tcu.librarymanagement.service;

import cn.tcu.librarymanagement.entity.Book;
import cn.tcu.librarymanagement.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public List<Book> getAllBooks() {
        return bookMapper.findAll();
    }

    public Book getBookById(Integer id) {
        return bookMapper.findById(id);
    }

    public int addBook(Book book) {
        return bookMapper.add(book);
    }

    public int updateBook(Book book) {
        return bookMapper.update(book);
    }

    public int deleteBook(Integer id) {
        return bookMapper.delete(id);
    }
}
