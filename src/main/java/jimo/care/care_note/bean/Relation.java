package jimo.care.care_note.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <p>
 *  用于记录User、Setting、Module关系实体类
 * </p>
 * @author JIMO
 * @since 2022-08-04
 */
@Data
public class Relation implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer uId;

    private Integer sId;

    private Integer mId;

    public Relation() {
    }

}
