package cn.tcu.librarymanagement.service.impl;

import cn.tcu.librarymanagement.entity.Book;
import cn.tcu.librarymanagement.mapper.BookMapper;
import cn.tcu.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.tcu.librarymanagement.mapper.BorrowRecordMapper;
import cn.tcu.librarymanagement.entity.BorrowRecord;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BorrowRecordMapper borrowRecordMapper;

    @Override
    public void addBook(Book book) {
        bookMapper.add(book);
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookMapper.findAll();
        for (Book book : books) {
            if ("已借".equals(book.getStatus())) {
                List<BorrowRecord> records = borrowRecordMapper.findByBookId(book.getId());
                for (BorrowRecord record : records) {
                    if ("借阅中".equals(record.getStatus())) {
                        book.setBorrowUserId(record.getUserId());
                        break;
                    }
                }
            } else {
                book.setBorrowUserId(null);
            }
        }
        return books;
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookMapper.findById(id);
    }

    @Override
    public void deleteBook(Long id) {
        bookMapper.delete(id);
    }
}
