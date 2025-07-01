package cn.tcu.librarymanagement.controller;

import cn.tcu.librarymanagement.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/borrow")
public class BorrowRecordController {
    @Autowired
    private BorrowRecordService borrowRecordService;

    @PostMapping("/borrowBook")
    public String borrowBook(@RequestParam Long bookId, HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) return "redirect:/login";
        Long userId;
        if (userIdObj instanceof Integer) {
            userId = ((Integer) userIdObj).longValue();
        } else if (userIdObj instanceof Long) {
            userId = (Long) userIdObj;
        } else {
            return "redirect:/login";
        }
        boolean success = borrowRecordService.borrowBook(userId, bookId);
        if (success) {
            return "redirect:/books/list?msg=borrowSuccess";
        } else {
            return "redirect:/books/list?msg=borrowFail";
        }
    }

    @PostMapping("/returnBook")
    public String returnBook(@RequestParam Long bookId, HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) return "redirect:/login";
        Long userId;
        if (userIdObj instanceof Integer) {
            userId = ((Integer) userIdObj).longValue();
        } else if (userIdObj instanceof Long) {
            userId = (Long) userIdObj;
        } else {
            return "redirect:/login";
        }
        boolean success = borrowRecordService.returnBook(userId, bookId);
        if (success) {
            return "redirect:/books/list?msg=returnSuccess";
        } else {
            return "redirect:/books/list?msg=returnFail";
        }
    }
} 