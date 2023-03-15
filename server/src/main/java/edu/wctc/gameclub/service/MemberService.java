package edu.wctc.gameclub.service;

import edu.wctc.gameclub.entity.Member;
import edu.wctc.gameclub.exception.ResourceNotFoundException;
import edu.wctc.gameclub.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        List<Member> list = new ArrayList<>();
        memberRepository.findAll().forEach(list::add);
        return list;
    }

    public Member getMember(String email) throws ResourceNotFoundException {
        Optional<Member> optional = memberRepository.findByEmail(email);
        return optional.orElseThrow(() -> new ResourceNotFoundException("Member", "email", email));
    }

    // New for v2
    public Member getMember(int memberId) throws ResourceNotFoundException {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "id", memberId));
    }


}
