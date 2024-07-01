package hseneca.personal_website.service;

import hseneca.personal_website.entity.DataStatisticsForAge;
import hseneca.personal_website.repository.DataStatisticsForAgeRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private final DataStatisticsForAgeRepository dataStatisticsForAgeRepository;

    public SseService(DataStatisticsForAgeRepository dataStatisticsForAgeRepository) {
        this.dataStatisticsForAgeRepository = dataStatisticsForAgeRepository;
    }

    public SseEmitter addEmitter() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));
        return emitter;
    }

    @Scheduled(fixedRate = 10000)
    public void sendEvents() {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        List<DataStatisticsForAge> stats = dataStatisticsForAgeRepository.findAll();
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().name("stats").data(stats));
            } catch (IOException e) {
                emitter.complete();
                deadEmitters.add(emitter);
            }
        }
        emitters.removeAll(deadEmitters);
    }
}
