package book.manager.service.impl;

import book.manager.entity.Book;
import book.manager.entity.Borrow;
import book.manager.entity.BorrowDetails;
import book.manager.mapper.BookMapper;
import book.manager.mapper.UserMapper;
import book.manager.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper mapper;
    @Resource
    UserMapper userMapper;

    @Override
    public List<Book> getAllBook() {
        return mapper.allBook();
    }

    @Override
    public List<Book> getAllBookWithOutBorrow() {
        List<Book> books = mapper.allBook();
        List<Integer> borrows = mapper.borrowList()
                .stream()
                .map(Borrow::getBid)
                .collect(Collectors.toList());
        return books
                .stream()
                .filter(book -> !borrows.contains(book.getBid()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBorrowedBookById(int id) {
        Integer sid = userMapper.getSidByUserId(id);
        if(sid == null) return Collections.emptyList();
        return mapper.borrowListBySid(sid)
                .stream()
                .map(borrow -> mapper.getBookById(borrow.getBid()))
                .collect(Collectors.toList());
    }

    /*
    * 管理员删除书籍
    * */
    @Override
    public void deleteBook(int bid) {
        mapper.deleteBook(bid);
    }
    /*
    * 管理员添加书籍
    * */
    @Override
    public void addBook(String title, String desc, double price) {
        mapper.addBook(title, desc, price);
    }
    /*
    * 用户根据自己的id来借书，管理员添加借阅信息
    * */
    @Override
    public void borrowBook(int bid, int id) {
        Integer sid = userMapper.getSidByUserId(id);
        if(sid == null) return;
        mapper.addBorrow(bid, sid);
    }
    /*
    * 用户根据自己的id来还书，管理员删除借阅信息
    * */
    @Override
    public void returnBook(int bid, int id) {
        Integer sid = userMapper.getSidByUserId(id);
        if(sid == null) return;
        mapper.deleteBorrow(bid, sid);
    }
    /*
    * 获取详细的借阅信息
    * */
    @Override
    public List<BorrowDetails> getBorrowDetailsList() {
        return mapper.borrowDetailsList();
    }
}
