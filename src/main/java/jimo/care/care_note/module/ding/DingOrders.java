package jimo.care.care_note.module.ding;

import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;
import jimo.care.care_note.module.SendMessage;
import jimo.care.care_note.util.CodeUtils;
import jimo.care.care_note.util.DateUtil;
import jimo.care.care_note.util.DingWebhook;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/***
 * 钉钉进程订单消息模板
 */
@Component
public class DingOrders  {
    @Resource
    DingWebhook dingWebhook;
    /***
     * @param stringList 填入必要的信息参数[name,ID,Money,套餐，留言，User]
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
/*(｡･∀･)ﾉﾞ嗨，MAN ！ @全员

有新订单了 (●ˇ∀ˇ●) -

**下单用户:JIMO(ID:1)**

冲之前余额为:99次

他在：2022-08-13 15:51:57 时，选择的套餐为：包月（8元/90次）

*该用户支付留言:真好玩，支持大佬！*

*他的其他信息User(id=1, name=null, pwd=null, email=null, power=7, phone=null, bz=null, money=null, createTime=null, loginTime=null, sex=null)*

群里的各位运维大哥，来活了，谁去操作一下？🤭去处理的大兄弟==别忘了回复我一下吆==！（害怕多冲了，那我们可赔了）

==此次消息防重码为：087591==*/
    /***
     * @return MD格式消息
     * 图签：![screenshot](http://jimo.fun/img/2222.jpg)
     */
    public OapiRobotSendRequest md(List<String> stringList) {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("Care-Note:我是JIMO自动运维助手 ，不服来战呀！！");
        markdown.setText("### (｡･∀･)ﾉﾞ嗨，MAN ！ @全员\n" +
                "> \n\n" +
                "<span style='color:red'>有新订单了 (●ˇ∀ˇ●) - </span><br><br>\n\n" +
                "**下单用户:<span style='color:yellow'>"+stringList.get(0)+"(ID:"+stringList.get(1)+")</span>**\n\n" +
                "<br>冲之前余额为:<span style='color:green'>"+stringList.get(2)+"次</span>\n\n" +
                "操作时间：<span style='color:blue'>"+ DateUtil.localDateTimeToString(LocalDateTime.now())+"</span>\n\n" +
                "选择的套餐为：<span style='color:blue'>"+stringList.get(3)+" </span>\n\n" +
                "*<br><br>该用户支付留言:<span>"+stringList.get(4)+"</span>*\n\n" +
                "*<br>他的其他信息:<span>"+stringList.get(5)+"</span>*\n\n" +
                "<br><br><span style=\"font-size: 14px;background: linear-gradient(to right, red, blue);-webkit-background-clip: text;color: transparent;\"> 群里的各位运维大哥，来活了，谁去操作一下？❤🤭</span><br>" +
                "去处理的大兄弟别忘了回复我一下吆==！（害怕多冲了，那我们可赔了）\n\n" +
                "==此次消息防重码为："+ CodeUtils.getCode()+"==*/");
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
