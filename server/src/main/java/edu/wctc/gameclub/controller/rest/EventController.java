package edu.wctc.gameclub.controller.rest;

import edu.wctc.gameclub.entity.Event;
import edu.wctc.gameclub.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Address of IntelliJ's web preview server
//@CrossOrigin(origins="http://localhost:63342")
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return event;
    }

    @PutMapping
    public Event updateEvent(@RequestBody Event event) {
        // PUT will always update a new event, never create
        eventService.updateEvent(event);
        return event;
    }

    @DeleteMapping("/{eventId}")
    public String deleteEvent(@PathVariable int eventId) {
        eventService.delete(eventId);
        return "Successfully deleted Event ID: " + eventId;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{eventId}")
    public Event getEvent(@PathVariable int eventId) {
        return eventService.getEvent(eventId);
    }
}
