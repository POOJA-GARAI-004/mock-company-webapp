package com.societymanagement.repository;

import com.societymanagement.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, Long> { }