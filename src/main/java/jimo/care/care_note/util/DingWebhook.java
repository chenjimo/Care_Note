package jimo.care.care_note.util;

import jimo.care.care_note.module.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/***
 * 钉钉消息处理类
 */
@Component
public class DingWebhook {
    @Value("${jimo.api-util.webhookURL}")
    private String robotWebhook;

    @Value("${jimo.api-util.webhookKey}")
    private String robotKey;

    @Autowired
    private RestTemplate restTemplate;

    public boolean send(List<String> stringList, SendMessage sendMessage){
        System.out.println("@phone:"+stringList.get(0)+"发送了"+sendMessage.text(stringList));
        return true;
    }

}

