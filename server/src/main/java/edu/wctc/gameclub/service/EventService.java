package edu.wctc.gameclub.service;

import edu.wctc.gameclub.entity.Event;
import edu.wctc.gameclub.exception.ResourceNotFoundException;
import edu.wctc.gameclub.repo.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void delete(int eventId) {
        eventRepository.deleteById(eventId);
    }

    public List<Event> getAllEvents() {
        List<Event> list = new ArrayList<>();
        eventRepository.findAll().forEach(list::add);
        return list;
    }

    public Event getEvent(int id) {
        return eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", id));
    }

    public void createEvent(Event event) {
        // If client sent an ID, ignore it
        // POST will always create a new event, never update
        event.setId(0);
        eventRepository.save(event);
    }

    public void updateEvent(Event event) {
        eventRepository.save(event);
    }
}
