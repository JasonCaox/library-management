package cn.tcu.librarymanagement.service.impl;

import cn.tcu.librarymanagement.entity.BorrowRecord;
import cn.tcu.librarymanagement.mapper.BorrowRecordMapper;
import cn.tcu.librarymanagement.mapper.BookMapper;
import cn.tcu.librarymanagement.entity.Book;
import cn.tcu.librarymanagement.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {
    @Autowired
    private BorrowRecordMapper borrowRecordMapper;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public boolean borrowBook(Long userId, Long bookId) {
        Book book = bookMapper.findById(bookId);
        if (book == null || !"可借".equals(book.getStatus())) {
            return false;
        }
        // 新建借阅记录
        BorrowRecord record = new BorrowRecord();
        record.setUserId(userId);
        record.setBookId(bookId);
        record.setBorrowTime(new Date());
        record.setStatus("借阅中");
        int inserted = borrowRecordMapper.insertBorrowRecord(record);
        // 更新图书状态
        book.setStatus("已借");
        bookMapper.updateBook(book);
        return inserted > 0;
    }

    @Override
    public boolean returnBook(Long userId, Long bookId) {
        List<BorrowRecord> records = borrowRecordMapper.findByBookId(bookId);
        BorrowRecord current = null;
        for (BorrowRecord r : records) {
            if (r.getUserId().equals(userId) && "借阅中".equals(r.getStatus())) {
                current = r;
                break;
            }
        }
        if (current == null) return false;
        // 更新归还时间和状态
        current.setReturnTime(new Date());
        current.setStatus("已归还");
        borrowRecordMapper.updateReturnTime(current.getId(), current.getReturnTime());
        // 更新图书状态
        Book book = bookMapper.findById(bookId);
        book.setStatus("可借");
        bookMapper.updateBook(book);
        return true;
    }

    @Override
    public List<BorrowRecord> getBorrowRecordsByUser(Long userId) {
        return borrowRecordMapper.findByUserId(userId);
    }

    @Override
    public List<BorrowRecord> getBorrowRecordsByBook(Long bookId) {
        return borrowRecordMapper.findByBookId(bookId);
    }

    @Override
    public List<BorrowRecord> getAllRecords() {
        return borrowRecordMapper.findAll();
    }
} 