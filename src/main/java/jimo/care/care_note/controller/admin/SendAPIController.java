package jimo.care.care_note.controller.admin;

import jimo.care.care_note.config.SendConfig;
import jimo.care.care_note.info.send.SendEmail;
import jimo.care.care_note.module.weather.WeatherEvening;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/api")
public class SendAPIController {
    @Resource
    SendConfig sendConfig;

    @GetMapping("/send/{time}")
    public String send(@PathVariable("time") String time) {
        String code = "不好意思，Care-Note暂未开放此API服务！！！";
        switch (time) {
            case "m": {
                sendConfig.scheduledMorning();
                code = "已执行---》》》scheduledMorning";
            }
            break;
            case "n":
            {
                sendConfig.scheduledNoon();
                code="已执行---》》》scheduledNoon";
            }
                break;
            case "e":
            {
                sendConfig.scheduledEvening();
                code="已执行---》》》scheduledEvening";
            }
                break;
            case "l":
            {
                sendConfig.scheduledOlg();
                code="已执行---》》》scheduledOlg";
            }
                break;
        }

        return code;
    }
}
