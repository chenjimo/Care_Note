package jimo.care.care_note.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;

/**
 * <p>
 * 用于记录关怀对象的实体类
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

    public Setting() {
    }
}
