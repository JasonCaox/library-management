package cn.tcu.librarymanagement.service;

import cn.tcu.librarymanagement.entity.Book;
import java.util.List;

public interface BookService {
    void addBook(Book book);
    List<Book> getAllBooks();
    void updateBook(Book book);
    Book getBookById(Integer id);
    void deleteBook(Integer id);
}
