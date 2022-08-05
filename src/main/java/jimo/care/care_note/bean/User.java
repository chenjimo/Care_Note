package jimo.care.care_note.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <p>
 * 用于记录用户信息的实体类
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

    public User(String name, String pwd, String email, Integer power, String phone, String bz, Integer money) {
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.power = power;
        this.phone = phone;
        this.bz = bz;
        this.money = money;
    }

    public User(Integer id, Integer power) {
        this.id = id;
        this.power = power;
    }

    public User() {
    }
}
