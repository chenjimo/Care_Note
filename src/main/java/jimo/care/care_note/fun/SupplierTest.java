package jimo.care.care_note.fun;

import jimo.care.care_note.util.APIUtil;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.function.Supplier;

/***
 * 定时消息业务处理类
 * 无参无返类型
 */
@Component("supplierTest")
public class SupplierTest implements Supplier<String> {
    @Resource
    APIUtil apiUtil;
    @Override
    public String get() {
       // String weather = apiUtil.getWeather("南阳");
       // System.out.println(weather);
        return null;
    }
}
