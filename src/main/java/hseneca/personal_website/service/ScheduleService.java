package hseneca.personal_website.service;

import hseneca.personal_website.model.request.AgeGroupStatsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    UserService userService;

    @Scheduled(fixedRate = 10000)
    public void sendEven() {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        List<AgeGroupStatsDto> stats = userService.countUsersByAgeGroup();
        System.out.println(stats);
//        for (SseEmitter emitter : emitters) {
//            try {
//                emitter.send(SseEmitter.event().name("stats").data(stats));
//            }catch (IOException e){
//                emitter.complete();
//                deadEmitters.add(emitter);
//            }
//        }
//        emitters.removeAll(deadEmitters);
    }
}
