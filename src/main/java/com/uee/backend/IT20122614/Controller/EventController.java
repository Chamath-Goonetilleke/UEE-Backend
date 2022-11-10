package com.uee.backend.IT20122614.Controller;

import com.uee.backend.IT20122614.DTO.EventDTO;
import com.uee.backend.IT20122614.Service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
@CrossOrigin("*")
public class EventController {
    final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @PostMapping("/add-new")
    public ResponseEntity<?> saveEvent(@RequestBody EventDTO eventDTO){

        return eventService.saveEvent(eventDTO);
    }

    @GetMapping("/display-all-events")
    public ResponseEntity<?> getAllComplains(){

        return eventService.getAllEvents();
    }
}
