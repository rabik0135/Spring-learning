package com.example.EventListenerDemo.config;

import com.example.EventListenerDemo.EventQueue;
import com.example.EventListenerDemo.EventQueueWorker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(EventQueue.class)
public class EventQueueConfiguration {

    @Bean
    public EventQueueWorker eventQueueWorker(EventQueue eventQueue, ApplicationEventPublisher publisher) {
        return new EventQueueWorker(eventQueue, publisher);
    }
}
