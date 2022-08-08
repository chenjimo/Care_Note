package jimo.care.care_note.info.send;

import jimo.care.care_note.util.APIUtil;
import jimo.care.care_note.util.LimitUtil;
import lombok.Data;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * 适配器类，为了防止由于后期API变革导致的使用异常
 * 为了更好的适配API接口
 * </p>
 *
 * @author JIMO
 * @since 2022-08-07
 */
@Data
@Component
public class SendPhone {
    private  String phone;
    private  String m1;
    private  String m2;
    private  String m3;
    private  String m4;

    @Resource
    APIUtil apiUtil;
    public String open(){
        LimitUtil limitUtil = new LimitUtil();
        return apiUtil.sendPhone(phone,
                limitUtil.stringLimit(m1),
                limitUtil.stringLimit(m2),
                limitUtil.stringLimit(m3),
                limitUtil.stringLimit(m4));
    }
}
