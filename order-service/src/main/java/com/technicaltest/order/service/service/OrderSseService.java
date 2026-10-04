package com.technicaltest.order.service.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.technicaltest.order.service.model.Order;

import tools.jackson.databind.ObjectMapper;

@Service 
public class OrderSseService {
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper;

    OrderSseService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter)); 
        return emitter;
    }

    public void send(Order order)
    {
        String json = objectMapper.writeValueAsString(order);

        for (SseEmitter emitter: emitters)
        {
            try{
                emitter.send(
                    SseEmitter.event().name("order-status")
                    .data(json)
                );
            } catch(Exception exception){
                System.err.println("Error enviando SSE: " + exception.getMessage());
                emitters.remove(emitter);
            }                    
        }        
    }
}
