package jimo.care.care_note;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/***
 * 启动类
 *  @author JIMO <a href="https://gitee.com/chenjimo/">作者仓库地址<a/>;<a href="https://yuque.com/jimoworld/">作者文库地址<a/>
 *  @since 2022-08-06 <a href="https://gitee.com/chenjimo/Care_Note">该项目地址<a/>
 */
@SpringBootApplication
@EnableScheduling //定时器注解
@MapperScan("jimo.care.care_note.mapper")
public class CareNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareNoteApplication.class, args);
    }

    @Bean
    public RestTemplate RestTemplate(){
        return new RestTemplate();
}
}
