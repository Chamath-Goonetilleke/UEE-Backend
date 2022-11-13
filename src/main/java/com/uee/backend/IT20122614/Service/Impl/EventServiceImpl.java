package com.uee.backend.IT20122614.Service.Impl;

import com.uee.backend.IT20122614.DTO.EventDTO;
import com.uee.backend.IT20122614.Model.Blog;
import com.uee.backend.IT20122614.Model.Complain;
import com.uee.backend.IT20122614.Model.Event;
import com.uee.backend.IT20122614.Repository.EventRepository;
import com.uee.backend.IT20122614.Service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class EventServiceImpl implements EventService {
    final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public ResponseEntity<?> saveEvent(EventDTO eventDTO) {
        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));

            Event eventSave = new Event();
            eventSave.setTitle(eventDTO.getTitle());
            eventSave.setUserName(eventDTO.getUserName());
            eventSave.setImageUrl(eventDTO.getImageUrl());
            eventSave.setContent(eventDTO.getContent());
            eventSave.setTime(eventDTO.getTime());
            eventSave.setDate(eventDTO.getDate());
            eventSave.setCreatedDate(dtf.format(now));

            eventRepository.save(eventSave);

            return ResponseEntity.status(HttpStatus.OK).body("Successfully created");


        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot create new Event");
        }
    }

    @Override
    public ResponseEntity<?> getAllEvents() {
        try{
            List<Event> events = eventRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(events);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot create new Events");
        }
    }
}
