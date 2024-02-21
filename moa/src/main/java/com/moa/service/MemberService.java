package com.moa.service;

import com.moa.controller.form.MemberForm;
import com.moa.domain.Member;
import com.moa.domain.RoleStatus;
import com.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void join(MemberForm memberForm) {
        Member member = new Member(memberForm.getName(), RoleStatus.USER);
        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(value -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Member findOne(String memberName) {
        return memberRepository.findByName(memberName).orElse(null);
    }

    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    @Transactional
    public boolean update(MemberForm memberForm) {
        Optional<Member> findMember = memberRepository.findById(memberForm.getId());
        if (findMember.isPresent()) {
            Member member = findMember.get();

            if (member.getName().equals(memberForm.getName())) return false;

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
