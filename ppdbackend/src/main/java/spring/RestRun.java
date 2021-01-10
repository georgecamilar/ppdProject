package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import spring.controller.RestControllerPPD;

@SpringBootApplication
public class RestRun {
    public static void main(String[] args){
        SpringApplication.run(RestRun.class, args);
    }
}
