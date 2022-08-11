package jimo.care.care_note.info.log;

import lombok.Data;

/***
 * 针对中心大屏回馈数据的封装
 */
@Data
public class CountThree {
    private Integer total;
    private Integer update;
    private Integer success;

    public CountThree(Integer total, Integer update, Integer success) {
        this.total = total;
        this.update = update;
        this.success = success;
    }
}
