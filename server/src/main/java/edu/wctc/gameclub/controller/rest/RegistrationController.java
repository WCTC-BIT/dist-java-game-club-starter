package edu.wctc.gameclub.controller.rest;

import edu.wctc.gameclub.entity.Registration;
import edu.wctc.gameclub.exception.DuplicateRegistrationException;
import edu.wctc.gameclub.exception.ResourceNotFoundException;
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

    @PostMapping("/bad")
    public Registration register(@RequestBody Registration reg) {
        try {
            return registrationService.register(reg);
        } catch (DuplicateRegistrationException ex) {
            throw new RuntimeException(ex);
        } catch (ResourceNotFoundException ex) {
            throw new RuntimeException(ex);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid registration", e);
        }
    }
}
