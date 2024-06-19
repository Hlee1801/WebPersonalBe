package hseneca.personal_website;

import hseneca.personal_website.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataInsertionApplication {

    @Autowired
    private Test dataInsertionService;

    public static void main(String[] args) {
        SpringApplication.run(DataInsertionApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            dataInsertionService.insert();
        };
    }
}