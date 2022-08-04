package jimo.care.care_note.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

/***
 * 定时消息类
 */
@Component
public class TimeSchedule {
    @Autowired
    private Supplier<String> supplierTest;


    /***
     * 每天的上午七点执行一次！
     */
    @Scheduled(cron = "0 0 7 * * ?")
    public void scheduledMorning() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("在 "+formatter.format(new Date())+" 执行了方法。");
    }
    /***
     * 每天的中午十二点执行一次！
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void scheduledNoon() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("在 "+formatter.format(new Date())+" 执行了方法。");
    }
    /***
     * 每天的晚上九点执行一次！
     */
    @Scheduled(cron = "0 0 21 * * ?")
    public void scheduledEvening() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("在 "+formatter.format(new Date())+" 执行了方法。");
    }
    /***
     * 每天的晚上十点执行一次！
     */
    @Scheduled(cron = "0 0 22 * * ?")
    public void scheduledOlg() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("在 "+formatter.format(new Date())+" 执行了方法。");
    }
    /***
     * 每天的下午六点点执行一次！
     */
    @Scheduled(cron = "0 0 18 * * ?")
    public void scheduledHint() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("在 "+formatter.format(new Date())+" 执行了方法。");
    }

    @Scheduled(cron = "0/3 * * * * ? ")//测试每五秒一执行
    public void scheduledTest() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("在 "+formatter.format(new Date())+" 执行了方法。");
        supplierTest.get();
    }

}
