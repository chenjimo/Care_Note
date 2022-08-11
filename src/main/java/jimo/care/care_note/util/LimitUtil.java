package jimo.care.care_note.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/***
 * 字符限制工具
 */
@Component
public class LimitUtil {
    @Value("${jimo.api-util.stringLimit}")
    private int stringLimit =33;//防止yml数据读取不到
    /***
     * @param s 对原数据进行限定
     * @return 返回合格长度
     */
    public String stringLimit(String s){

        if (s.length()>stringLimit) {
            s=s.substring(0,stringLimit-3)+"...";
        }
        return s;
    }

    /***
     * @param s 传入字符串
     * @return 回馈加密信息
     */
    public String encode(String s){
        if (s.length()==1){
            s = "*";
        }else if (s.length()==2){
            s = s.substring(0,1)+"*";
        }else if (s.length()==3){
            s = s.substring(0,1)+"*"+s.substring(2,3);
        }else if (s.length()>3){
            String s1 = "";
            for (int i =0;i<s.length()/2;i++){
                s1 = "*"+s1;
            }
            s = s.substring(0,s.length()/2-1)+s1+
                    s.substring(s.length()/2-1+s1.length());
        }
        return s;
    }

}
