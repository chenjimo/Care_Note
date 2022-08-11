package jimo.care.care_note.info.log;

import lombok.Data;

/***
 * 用于记录不同地区的请求信息
 */
@Data
public class LocalInfo {
    private String local;
    private Integer visit;

    public LocalInfo(String local, Integer visit) {
        this.local = local;
        this.visit = visit;
    }
}
