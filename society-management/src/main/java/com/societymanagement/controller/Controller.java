package com.societymanagement.controller;

import com.societymanagement.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
/*    @Autowired
    private MemberServices memberServices;*/

    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/home")
    public String homePage() {
        return "index.html";
    }

    @GetMapping("/delete")//TODO: will delete these once development is done.
    public String delMember() {
            memberRepository.deleteAll();
        return "memberList.html";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login.html";
    }

    @GetMapping("/visitor")
    public String visitorPage() {
        return "visitor.html";
    }


    @GetMapping("/member-list")
    public String list(Model model){
        model.addAttribute("ganpat", memberRepository.findAll());
      /*  ModelAndView  obj = new ModelAndView();
        obj.getModelMap().put("members",model);
        obj.setViewName("memberList.html");*/
        return "memberList.html";
    }
}


