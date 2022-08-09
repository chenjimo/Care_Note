package jimo.care.care_note.util;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

/***
 * 测试钉钉的接口调试！！！
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebhokTest {
    @Resource
    DingWebhook dingWebhook;
    @Value("${jimo.api-util.webhookURL}")
    private String robotWebhook;

    @Value("${jimo.api-util.webhookKey}")
    private String robotKey;

    @Test
    void ding() throws ApiException {

    }


    public static void main(String[] args) throws ApiException {
        //  sendMessageWebhook();
        sendMessageWebhook();
    }

    /* curl 'https://oapi.dingtalk.com/robot/send?access_token=290006f7f165f5dc1f505eb03f2d0c7d53b546b35eb75157f3acf9244daf9204'
-H 'Content-Type: application/json'
-d '{"msgtype": "markdown","markdown":
{"content":"Care-Note:我是JIMO自动运维助手 ，不服来战呀！！
\n\n！![screenshot](http://jimo.fun/img/2222.jpg)"这是俺图像，这样的运维您喜欢吗？}}'
 */
    private static void sendMessageWebhook() throws ApiException {
        Date time = new Date();
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=290006f7f165f5dc1f505eb03f2d0c7d53b546b35eb75157f3acf9244daf9204&timestamp=" + LocalDateTime.now());
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
        //link格式
        link(request);
        //markdown格式
        md(request);
        OapiRobotSendResponse response = client.execute(request);
        System.out.println(response);
    }

    private static void link(OapiRobotSendRequest request) {
        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl("https://www.dingtalk.com/");
        link.setPicUrl("");
        link.setTitle("时代的火车向前开");
        link.setText("这个即将发布的新版本，创始人xx称它为红树林。而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是红树林");
        request.setLink(link);
    }

    private static void md(OapiRobotSendRequest request) {
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("Care-Note:我是JIMO自动运维助手 ，不服来战呀！！");
        markdown.setText("#### (｡･∀･)ﾉﾞ嗨，MAN ！ @全员\n" +
                "> \n\n" +
                "> ![screenshot](http://jimo.fun/img/2222.jpg)\n" +
                "> ###### 这是俺图像，这样的运维您喜欢吗？\n");
        request.setMarkdown(markdown);
    }
}
