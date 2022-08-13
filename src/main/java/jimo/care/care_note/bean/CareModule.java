package jimo.care.care_note.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * 用于记录消息模板实体类
 * morning、noon、evening分别是早上中午晚上的自定义消息内容不设置会有默认信息。
 * use：默认0公开模板中性，-1、-2分别为公开男性女性模板，私用的为所属用户主键。
 * 每个信息模板内容均不可超过28个字符。
 * </p>
 * @author JIMO
 * @since 2022-08-04
 */
@Data
@TableName("module")
public class CareModule implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String status;

    private String temp;

    private String morning;

    private String noon;

    private String evening;

    private Integer uId;

    @TableField(exist = false)
    private Integer visit;
    public CareModule() {
    }

    public CareModule(Integer id, Integer uId) {
        this.id = id;
        this.uId = uId;
    }
}
