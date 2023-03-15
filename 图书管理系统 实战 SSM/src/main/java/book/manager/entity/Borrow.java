package book.manager.entity;

import lombok.Data;

import java.util.Date;

/**
 *书籍借阅信息
 */
@Data
public class Borrow {
    int id;
    int bid;
    int sid;
    Date date;
}
