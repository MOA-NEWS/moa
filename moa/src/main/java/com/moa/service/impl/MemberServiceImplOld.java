package com.moa.service.impl;

import com.moa.controller.form.MemberForm;
import com.moa.domain.old.Member;
import com.moa.domain.RoleStatus;
import com.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImplOld {
    private final MemberRepository memberRepository;

    @Transactional
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Transactional
    public void join(MemberForm memberForm) {
        Member member = new Member(memberForm.getName(), RoleStatus.USER);
        validateDuplicateMember(member.getName());
        memberRepository.save(member);
    }

    private void validateDuplicateMember(String memberName) {
        memberRepository.findByName(memberName)
                .ifPresent(value -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다. : " + memberName);
                });
    }

    public Member findOne(String memberName) {
        return memberRepository.findByName(memberName).orElse(null);
    }

    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    // 전체 유저 검색
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    // 권한 검색
    public List<Member> findAllByRole(String role) {
        return memberRepository.findAllByRole(RoleStatus.valueOf(role));
    }
    @Transactional
    public boolean update(MemberForm memberForm) {

        Optional<Member> findMember = memberRepository.findById(memberForm.getId());
        if (findMember.isPresent()) {
            Member member = findMember.get();

            if (member.getName().equals(memberForm.getName())) return false;
            validateDuplicateMember(memberForm.getName());

            member.setName(memberForm.getName());
        }
        return true;
    }

    @Transactional
    public boolean retire(Long memberId) {
        memberRepository.deleteById(memberId);
        return memberRepository.findById(memberId).isEmpty();
    }
}
