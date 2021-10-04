package edu.wctc.gameclub.controller.rest;

import edu.wctc.gameclub.entity.Registration;
import edu.wctc.gameclub.service.EventService;
import edu.wctc.gameclub.service.MemberService;
import edu.wctc.gameclub.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/rsvp")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private EventService eventService;


    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    @PostMapping("/bad")
    public Registration makeReservation(@RequestBody Registration reg) {
        try {
            if (!registrationService.isRegistered(reg)) {
                registrationService.register(reg);
            }
            return reg;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid registration", e);
        }
    }
}
