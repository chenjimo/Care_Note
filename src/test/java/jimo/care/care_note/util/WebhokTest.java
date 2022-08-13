package jimo.care.care_note.util;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import jimo.care.care_note.bean.User;
import jimo.care.care_note.module.ding.DingOrders;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/***
 * 测试钉钉的接口调试！！！
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebhokTest {

    @Value("${jimo.api-util.webhookURL}")
    private String robotWebhook;
    @Resource
    DingWebhook dingWebhook;
    @Resource
    DingOrders dingOrders;


    @Test
    void ding() throws ApiException {
        List<String> list =new ArrayList<>();
        list.add("JIMO");
        list.add("1");
        list.add("99");
        list.add("包月（8元/90次）");
        list.add("真好玩，支持大佬！");
        list.add(new User(1,7).toString());
        dingWebhook.send(dingOrders.md(list));
    }


    public static void main(String[] args) throws ApiException {
        //  sendMessageWebhook();
        sendMessageWebhook(md());
    }

    private static void sendMessageWebhook(OapiRobotSendRequest request) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=290006f7f165f5dc1f505eb03f2d0c7d53b546b35eb75157f3acf9244daf9204&timestamp=" + LocalDateTime.now());
        OapiRobotSendResponse response = client.execute(request);
    }

    /* curl 'https://oapi.dingtalk.com/robot/send?access_token=290006f7f165f5dc1f505eb03f2d0c7d53b546b35eb75157f3acf9244daf9204'
-H 'Content-Type: application/json'
-d '{"msgtype": "markdown","markdown":
{"content":"Care-Note:我是JIMO自动运维助手 ，不服来战呀！！
\n\n！![screenshot](http://jimo.fun/img/2222.jpg)"这是俺图像，这样的运维您喜欢吗？}}'
 */
    /***
     * @return MD格式消息
     */
    private static OapiRobotSendRequest md() {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("Care-Note:我是JIMO自动运维助手 ，不服来战呀！！");
        markdown.setText("### (｡･∀･)ﾉﾞ嗨，MAN ！ @全员\n" +
                "> \n\n" +
                ">##有新订单了 \n" +
                ">#name:JIMO \n" +
                ">#money：1314 次 \n" +
                ">#他在："+ DateUtil.localDateTimeToString(LocalDateTime.now())+" 时\n" +
                ">#充值了：521 次 \n" + //![screenshot](http://jimo.fun/img/2222.jpg)
                "> #ID：1\n");
        request.setMarkdown(markdown);
        return request;
    }
    /***
     * @return Link格式消息
     */
    public OapiRobotSendRequest link() {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl("https://www.dingtalk.com/");
        link.setPicUrl("");
        link.setTitle("时代的火车向前开");
        link.setText("这个即将发布的新版本，创始人xx称它为红树林。而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是红树林");
        request.setLink(link);
        return request;
    }

    /***
     * @return Text格式消息
     */
    public OapiRobotSendRequest text(){
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("测试文本消息");
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList("132xxxxxxxx"));
        // isAtAll类型如果不为Boolean，请升级至最新SDK
        at.setIsAtAll(true);
        at.setAtUserIds(Arrays.asList("109929", "32099"));
        request.setAt(at);
        return request;
    }
}
