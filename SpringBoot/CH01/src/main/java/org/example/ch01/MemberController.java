package org.example.ch01;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/test")
    public List<Member> getAllMember() {
        List<Member> members = memberService.getAllMembers();
        return members;
    }
}
