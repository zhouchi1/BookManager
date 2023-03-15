package book.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 总体信息->学生数量、书籍数量、借阅数量
 */
@Data
@AllArgsConstructor//生成全参构造函数
public class GlobalStat {
    int userCount;
    int bookCount;
    int borrowCount;
}
