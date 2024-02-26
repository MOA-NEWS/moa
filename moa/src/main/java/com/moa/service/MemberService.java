package com.moa.service;

import com.moa.controller.form.MemberForm;
import com.moa.domain.Member;

import java.util.List;

public interface MemberService {
    boolean join(MemberForm memberForm);

    Member findOne(String memberName);

    Member findOne(Long memberId);

    List<Member> findAll();

    List<Member> findAllByRole(String role);

    boolean update(MemberForm memberForm);

    boolean retire(Long memberId);


}
