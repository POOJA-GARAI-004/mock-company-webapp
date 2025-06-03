package com.societymanagement.services.impl;

import com.societymanagement.entity.Members;
import com.societymanagement.repository.MemberRepository;
import com.societymanagement.services.MemberServices;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberServicesImpl implements MemberServices {
    private MemberRepository memberRepository;

    public MemberServicesImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public List<Members> getAllMembers() {

        return memberRepository.findAll();
    }

    @Override
    public Members saveStudent(Members members) {
        return memberRepository.save(members);
    }

    @Override
    public Members getStudentById(Long id) {
        return memberRepository.findById(id).get();
    }

    @Override
    public Members updateStudent(Members members) {
        return memberRepository.save(members);
    }

    @Override
    public void deleteStudentById(Long id) {
        memberRepository.deleteById(id);
    }
}
