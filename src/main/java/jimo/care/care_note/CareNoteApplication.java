package jimo.care.care_note;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

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
