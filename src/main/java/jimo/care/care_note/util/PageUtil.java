package jimo.care.care_note.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


/***
 * 通过自定义去实现分页工具
 */
public class PageUtil<T> {

    public Page<T> getList(){
        return new Page<T>(1,5);
    }
}
