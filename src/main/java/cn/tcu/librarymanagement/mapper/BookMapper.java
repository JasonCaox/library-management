package cn.tcu.librarymanagement.mapper;


import cn.tcu.librarymanagement.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> findAll() {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    public Book findById(Long id) {
        String sql = "SELECT * FROM book WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
    }

    public int add(Book book) {
        String sql = "INSERT INTO book(name, author, category, status) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getCategory(), book.getStatus());
    }

    public int updateBook(Book book) {
        String sql = "UPDATE book SET name=?, author=?, category=?, status=? WHERE id=?";
        return jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getCategory(), book.getStatus(), book.getId());
    }

    public int delete(Long id) {
        String sql = "DELETE FROM book WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }
}
