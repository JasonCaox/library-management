package cn.tcu.librarymanagement.controller;

import cn.tcu.librarymanagement.entity.Book;
import cn.tcu.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookPageController {

    @Autowired
    private BookService bookService;

    // 显示图书列表
    @GetMapping("/list")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book_list";
    }

    // 显示添加图书页面
    @GetMapping("/add")
    public String showAddBookForm() {
        return "add_book";
    }

    // 处理添加图书表单提交
    @PostMapping("/add")
    public String addBook(Book book) {
        bookService.addBook(book);
        return "redirect:/books/list"; // 添加成功后跳转到列表页
    }
}
