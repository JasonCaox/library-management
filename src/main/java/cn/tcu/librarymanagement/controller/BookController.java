package cn.tcu.librarymanagement.controller;

import cn.tcu.librarymanagement.entity.Book;
import cn.tcu.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public String addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return "添加成功";
    }

    @PutMapping
    public String updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return "修改成功";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return "删除成功";
    }
}
