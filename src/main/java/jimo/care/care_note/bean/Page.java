package jimo.care.care_note.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/***
 * <p>
 * 用于存储菜单数据的实体类
 * name：菜单名字，
 * url：地址，
 * power：用户权限同上用户权限对应，
 * bz：备注，
 * visit：访问次数
 * </p>
 * @author JIMO
 * @since 2022-08-06
 */
@Data
public class Page {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String url;

    private Integer power;

    private String bz;

    private Integer visit;

    private String pageUrl;

    public Page() {
    }

    public Page(Integer id) {
        this.id = id;
    }

    public Page(Integer id, Integer power) {
        this.id = id;
        this.power = power;
    }
}
