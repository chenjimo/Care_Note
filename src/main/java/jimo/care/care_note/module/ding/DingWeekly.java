package jimo.care.care_note.module.ding;

import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;
import jimo.care.care_note.module.SendMessage;
import jimo.care.care_note.util.DingWebhook;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/***
 * 钉钉每日周报消息模板
 */
@Component
public class DingWeekly {
    @Resource
    DingWebhook dingWebhook;
    /***
     * @param stringList 填入必要的信息参数
     * @return 返回一个处理过的信息格式
     */
    public Object text(List<String> stringList) throws ApiException {
        dingWebhook.send(md(stringList));
        return true;
    }

    /***
     * @param map 存入对象信息
     * @return 返回标准消息格式
     */
    public String test(Map<Class, Object> map) {
        return null;
    }
    /***
     * @param stringList
     * @return 用于处理异常信息提示的信息！
     */
    private OapiRobotSendRequest md(List<String> stringList)  {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("Care-Note:我是JIMO自动运维助手 ，不服来战呀！！");
        markdown.setText("### 🤩O(∩_∩)O嗨，MAN ！ @全员\n" +
                "> \n\n" +
                "<span style='color:red'>每日日报-</span><br><br>\n\n" +
                "**日志记录JSON（JSON）:<span style='color:yellow'>"+stringList.get(0)+"</span>**\n\n" +
                "<br>截至今日当前时间:<span style='color:green'>"+stringList.get(1)+"</span>\n\n" +
                "注册的用户总数为：<span style='color:blue'>"+ stringList.get(2)+"</span>\n\n" +
                "创建的模板总数为：<span style='color:blue'>"+ stringList.get(3)+"</span>\n\n" +
                "关怀的对象总数为：<span style='color:blue'>"+ stringList.get(4)+"</span>\n\n" +
                "<br><br><span style=\"font-size: 14px;background: linear-gradient(to right, red, blue);-webkit-background-clip: text;color: transparent;\"> 专家们快来分析分析日志！🤭o(*￣▽￣*)ブ</span><br>" +
                "==此次消息防重码为："+ stringList.get(5)+"==*/");
        request.setMarkdown(markdown);
        return request;

    }
}
