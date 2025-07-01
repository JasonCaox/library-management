package cn.tcu.librarymanagement.mapper;

import cn.tcu.librarymanagement.entity.BorrowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class BorrowRecordMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertBorrowRecord(BorrowRecord record) {
        String sql = "INSERT INTO borrow_record (user_id, book_id, borrow_time, status) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, record.getUserId(), record.getBookId(), record.getBorrowTime(), record.getStatus());
    }

    public int updateReturnTime(Long id, Date returnTime) {
        String sql = "UPDATE borrow_record SET return_time = ?, status = '已归还' WHERE id = ?";
        return jdbcTemplate.update(sql, returnTime, id);
    }

    public List<BorrowRecord> findByUserId(Long userId) {
        String sql = "SELECT * FROM borrow_record WHERE user_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BorrowRecord.class), userId);
    }

    public List<BorrowRecord> findByBookId(Long bookId) {
        String sql = "SELECT * FROM borrow_record WHERE book_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BorrowRecord.class), bookId);
    }

    public List<BorrowRecord> findAll() {
        String sql = "SELECT * FROM borrow_record";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BorrowRecord.class));
    }
} 