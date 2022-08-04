package jimo.care.care_note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling //定时器注解
public class CareNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareNoteApplication.class, args);
    }

    @Bean
    public RestTemplate RestTemplate(){
        return new RestTemplate();
}
}
