package com.societymanagement.services;

import com.societymanagement.entity.Members;

import java.util.List;

public interface MemberServices {
    List<Members> getAllMembers();

    Members saveStudent(Members members);

    Members getStudentById(Long id);

    Members updateStudent(Members members);

    void deleteStudentById(Long id);
}
