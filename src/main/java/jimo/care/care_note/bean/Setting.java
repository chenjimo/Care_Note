package jimo.care.care_note.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用于记录关怀对象的实体类
 * status（int值）0表示暂停服务；1表示早安服务；2表示午安服务；4表示晚安服务；
 * 3=1+2服务、5=1+4服务、6=2+4服务、7=1+2+4服务。
 * sex：默认女=0，
 * law：为时间规律特性为以后增值功能做准备，设置为和cron相同的参数对应。
 * </p>
 *
 * @author JIMO
 * @since 2022-08-04
 */
@Data
public class Setting implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String local;

    private String phone;

    private Integer status;

    private Integer sex;

    private String law;
    @TableField(exist = false)
    private Integer visit;
    @TableField(exist = false)
    private Integer moduleId;

    @TableField(exist = false)
    private String moduleName;
    @TableField(exist = false)
    private String statusName;
    @TableField(exist = false)
    private List<CareModule> modules;


    public Setting() {
    }

    public Setting(Integer id, Integer status) {
        this.id = id;
        this.status = status;
    }

    public Setting(String name, String local, String phone, Integer status, Integer sex) {
        this.name = name;
        this.local = local;
        this.phone = phone;
        this.status = status;
        this.sex = sex;
    }
}
