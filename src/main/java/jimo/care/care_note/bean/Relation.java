package jimo.care.care_note.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <p>
 *  用于记录User、Setting、Module关系实体类
 *  u_id：用户主键；s_id：关怀对象主键；m_id：模板主键；
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

    public Relation(Integer uId, Integer sId, Integer mId) {
        this.uId = uId;
        this.sId = sId;
        this.mId = mId;
    }
}
