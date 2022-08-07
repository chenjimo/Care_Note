package jimo.care.care_note.module;

import java.util.List;
import java.util.Map;

/***
 * 默认的消息接口实现。
 */
public interface SendMessage {
    /***
     * @param stringList 填入必要的信息参数
     * @return 返回一个处理过的信息格式
     */
    Object text(List<String> stringList);

    /***
     * @param map 存入对象信息
     * @return 返回标准消息格式
     */
    String test(Map<Class,Object> map);
}
