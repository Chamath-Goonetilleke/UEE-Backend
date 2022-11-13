package com.uee.backend.IT20122614.Service;

import com.uee.backend.IT20122614.DTO.BlogDTO;
import com.uee.backend.IT20122614.DTO.EventDTO;
import org.springframework.http.ResponseEntity;

public interface EventService {
    ResponseEntity<?> saveEvent(EventDTO eventDTO);
    ResponseEntity<?> getAllEvents();
}
