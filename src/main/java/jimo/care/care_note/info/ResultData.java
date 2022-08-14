package jimo.care.care_note.info;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/***
 * @param <T> 将集合封装为 bootstrap-table识别的格式
 */
@Data
public class ResultData<T> {
    //每次查询的数据集合
    private List<T> rows = new ArrayList<>();
    //总数量
    private int total;

    public ResultData(List<T> rows, int total) {
        this.rows = rows;
        this.total = total;
    }
}
