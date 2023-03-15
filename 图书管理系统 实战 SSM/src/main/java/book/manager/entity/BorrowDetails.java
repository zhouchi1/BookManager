package book.manager.entity;

import lombok.Data;

import java.util.Date;

/**
 * 书籍借阅详细信息
 */
@Data
public class BorrowDetails {
    int id;
    String book_title;
    String user_name;
    Date time;
}
