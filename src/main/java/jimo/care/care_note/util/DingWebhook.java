package jimo.care.care_note.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class DingWebhook {
    @Value("${jimo.api-util.webhookURL}")
    private String robotWebhook;

    @Value("${jimo.api-util.webhookKey}")
    private String robotKey;

    @Autowired
    private RestTemplate restTemplate;


}

