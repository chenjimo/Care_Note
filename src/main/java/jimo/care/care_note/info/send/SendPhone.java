package jimo.care.care_note.info.send;

import jimo.care.care_note.util.APIUtil;
import jimo.care.care_note.util.LimitUtil;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
