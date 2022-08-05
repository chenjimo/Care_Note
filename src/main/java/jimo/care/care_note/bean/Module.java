package jimo.care.care_note.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <p>
 * 用于记录消息模板实体类
 * </p>
 * @author JIMO
 * @since 2022-08-04
 */
@Data
public class Module implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String status;

    private String temp;

    private String morning;

    private String noon;

    private String evening;

    private Integer use;

    public Module() {
    }

    public Module(Integer id, Integer use) {
        this.id = id;
        this.use = use;
    }
}
