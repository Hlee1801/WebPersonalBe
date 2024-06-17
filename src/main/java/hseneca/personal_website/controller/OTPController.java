package hseneca.personal_website.controller;
import hseneca.personal_website.service.KafkaMessagePublisher;
import hseneca.personal_website.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OTPController {

    @Autowired
    private OTPService otpService;

    @Autowired
    private KafkaMessagePublisher kafkaProducerService;

    @GetMapping("/send-otp")
    public String sendOTP(@RequestParam String email) {
        String otp = otpService.generateOTP();
        kafkaProducerService.sendMessage("otp_topic", email + ":" + otp);
        return "OTP sent to Kafka for " + otp;
    }
}


