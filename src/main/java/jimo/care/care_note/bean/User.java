package jimo.care.care_note.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用于记录用户信息的实体类
 * power：4修改权限，2消息提醒，1普通用户，0封禁或注销，默认注册均为1；
 * 3=1+2（用户消息提醒），5=1+4（管理用户权限），6=4+2（运维消息提醒权限）7全部权限+admin管控
 * money：实际为换算次数（例如：20元一季+270，8元一个月这里登记+90，按冲次数的一元+10）
 * create：创建时间，login：最近的活跃时间，sex：默认男生=1。
 * </p>
 * @author JIMO
 * @since 2022-08-04
 */
@Data
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String pwd;

    private String email;

    private Integer power;

    private String phone;

    private String bz;

    private Integer money;

    private LocalDateTime createTime;

    private LocalDateTime loginTime;

    private Integer sex;
    @TableField(exist = false)
    private List<Page> pages;
    @TableField(exist = false)
    private String ct;
    @TableField(exist = false)
    private String lt;
    public User(String name, String pwd, String email, Integer power, String phone, String bz, Integer money,Integer sex) {
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.power = power;
        this.phone = phone;
        this.bz = bz;
        this.money = money;
        this.sex=sex;
    }

    public User(Integer id, Integer power) {
        this.id = id;
        this.power = power;
    }

    public User() {
    }
}
