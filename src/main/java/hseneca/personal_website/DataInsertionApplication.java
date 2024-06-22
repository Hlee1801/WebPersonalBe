//package hseneca.personal_website;
//
//import hseneca.personal_website.entity.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import java.util.concurrent.CompletableFuture;
//
//@SpringBootApplication
//public class DataInsertionApplication {
//
//    @Autowired
//    private Test dataInsertionService;
//
//    public static void main(String[] args) {
//        SpringApplication.run(DataInsertionApplication.class, args);
//
//    }
//
//    @Bean
//    public CommandLineRunner run() {
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            dataInsertionService.insert();
//        });
//        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
//            dataInsertionService.insert();
//        });
//        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
//            dataInsertionService.insert();
//        });
//        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
//            dataInsertionService.insert();
//        });
//        CompletableFuture<Void> future4 = CompletableFuture.runAsync(() -> {
//            dataInsertionService.insert();
//        });
//        CompletableFuture<Void> future5 = CompletableFuture.runAsync(() -> {
//            dataInsertionService.insert();
//        });
//        CompletableFuture<Void> future6 = CompletableFuture.runAsync(() -> {
//            dataInsertionService.insert();
//        });
//        CompletableFuture<Void> future7 = CompletableFuture.runAsync(() -> {
//            dataInsertionService.insert();
//        });
//        return args -> {};
//    }
//}