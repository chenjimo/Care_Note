package jimo.care.care_note.bean;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <p>
 * 用于发送记录实体类
 * </p>
 * @author JIMO
 * @since 2022-08-04
 */
@Data
public class Log implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer uId;

    private Integer sId;

    private LocalDateTime date;

    private Integer mId;

    private String status;

    public Log(Integer uId, Integer sId, Integer mId, String status) {
        this.uId = uId;
        this.sId = sId;
        this.mId = mId;
        this.status = status;
    }

    public Log() {
    }

    public Log(Integer id, String status) {
        this.id = id;
        this.status = status;
    }
}
