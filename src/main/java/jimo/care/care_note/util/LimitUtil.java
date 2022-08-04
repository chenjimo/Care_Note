package jimo.care.care_note.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LimitUtil {
    @Value("${jimo.api-util.stringLimit}")
    private int stringLimit;

    /***
     * @param s 对原数据进行限定
     * @return 返回合格长度
     */
    public String stringLimit(String s){
        if (s.length()>stringLimit) {
            s=s.substring(0,stringLimit);
        }
        return s;
    }

}
