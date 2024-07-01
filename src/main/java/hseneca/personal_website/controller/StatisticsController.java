package hseneca.personal_website.controller;

import hseneca.personal_website.service.SseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/statics")
public class StatisticsController {

    @Autowired
    private SseService sseService;

    @GetMapping("/stats")
    public SseEmitter streamStats() {
        return sseService.addEmitter();
    }
}
