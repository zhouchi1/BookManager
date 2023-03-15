package book.manager.entity;

import lombok.Data;

/**
 *书的信息
 */
@Data
public class Book {
    int bid;
    String title;
    String desc;
    double price;
}
