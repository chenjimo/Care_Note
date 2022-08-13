package jimo.care.care_note.util;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import jimo.care.care_note.module.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/***
 * 钉钉消息处理类
 */
@Component
public class DingWebhook {
    @Value("${jimo.api-util.webhookURL}")
    private String robotWebhook;

    public void send(OapiRobotSendRequest request) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(robotWebhook + LocalDateTime.now());
        OapiRobotSendResponse response = client.execute(request);
    }
}

