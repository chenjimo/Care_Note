package jimo.care.care_note.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/***
 * QQEmail、Phone、GetWeather接口处理工具类
 */
@Component
public class APIUtil {

    @Value("${jimo.api-util.sendURL}")
    private String sendURL;//发消息API-URL
    @Value("${jimo.api-util.weatherURL}")
    private String weatherURL;//查天气API-URL
    @Value("${spring.mail.username}")
    private String from;//消息发送者mail
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private RestTemplate restTemplate;

    /***
     * 一个用于请求天气状况的API
     * @param local 城市名字
     * @return JSON格式的参数
     */
    public String getWeather(String local) {
        return restTemplate.getForObject(weatherURL + local, String.class);
    }

    /***
     * 请求发送消息的API
     * @param phone 手机号
     * @param name 接受对象名
     * @param status 天气情况
     * @param temp 温度
     * @param msg 留言
     * @return 返回True表示成功false失败
     */
    public boolean sendPhone(String phone, String name, String status, String temp, String msg) {
        String url = sendURL + phone + "&name=" + name + "&s1=" + status + "&s2=" + temp + "&s3=" + msg + "";
        return "oK".equals(restTemplate.getForObject(url, String.class));
    }

    /***
     * 用于给邮箱发送消息的API
     * @param to 发送者
     * @param subject 编码
     * @param msg 消息
     */
    public void sendMail(String to, String subject, String msg) {
        try {
            MimeMessage s = mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(s, true);
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(msg, true);
            mailSender.send(s);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
