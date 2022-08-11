package jimo.care.care_note.info.log;

import lombok.Data;

import java.util.List;
import java.util.Map;

/***
 * 对应绿线、蓝线、红线变化值
 */
@Data
public class ChangeCountData {
    private List<Map<String,Integer>> date1;
    private List<Map<String,Integer>> date2;
    private List<Map<String,Integer>> date3;

    public ChangeCountData(List<Map<String, Integer>> date1, List<Map<String, Integer>> date2, List<Map<String, Integer>> date3) {
        this.date1 = date1;
        this.date2 = date2;
        this.date3 = date3;
    }
}
