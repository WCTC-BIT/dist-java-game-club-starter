package edu.wctc.gameclub.service;

import edu.wctc.gameclub.entity.Event;
import edu.wctc.gameclub.entity.Member;
import edu.wctc.gameclub.entity.Registration;
import edu.wctc.gameclub.exception.DuplicateRegistrationException;
import edu.wctc.gameclub.exception.ResourceNotFoundException;
import edu.wctc.gameclub.repo.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepo registrationRepo;
    @Autowired
    private MemberService memberService;
    @Autowired
    private EventService eventService;

    public void cancel(int registrationId) {
        registrationRepo.deleteById(registrationId);
    }

    public List<Registration> getAllRegistrations() {
        List<Registration> list = new ArrayList<>();
        registrationRepo.findAll().forEach(list::add);
        return list;
    }

    public boolean isRegistered(Registration registration) throws ResourceNotFoundException {
        Member member = memberService.getMember(registration.getMember().getId());
        Event event = eventService.getEvent(registration.getEvent().getId());
        return registrationRepo.existsByMemberAndEvent(member, event);
    }

    public Registration register(Registration registration) throws DuplicateRegistrationException,
            ResourceNotFoundException {
        if (!isRegistered(registration)) {
            return registrationRepo.save(registration);
        } else {
            throw new DuplicateRegistrationException(registration.getMember().getEmail(),
                    registration.getEvent().getName());
        }
    }
}
