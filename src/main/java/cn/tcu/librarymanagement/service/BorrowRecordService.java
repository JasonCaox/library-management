package cn.tcu.librarymanagement.service;

import cn.tcu.librarymanagement.entity.BorrowRecord;
import java.util.List;

public interface BorrowRecordService {
    boolean borrowBook(Long userId, Long bookId);
    boolean returnBook(Long userId, Long bookId);
    List<BorrowRecord> getBorrowRecordsByUser(Long userId);
    List<BorrowRecord> getBorrowRecordsByBook(Long bookId);
    List<BorrowRecord> getAllRecords();
} 