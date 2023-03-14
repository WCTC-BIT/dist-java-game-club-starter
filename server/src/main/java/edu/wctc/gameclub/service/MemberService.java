package edu.wctc.gameclub.service;

import edu.wctc.gameclub.entity.Member;
import edu.wctc.gameclub.exception.ResourceNotFoundException;
import edu.wctc.gameclub.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member getMember(String email) {
        return memberRepository.findByEmail(email);
    }

    // New for v2
    public Member getMember(int memberId) throws ResourceNotFoundException {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "id", memberId));
    }


}
