package com.moa.service.impl;

import com.moa.controller.form.MemberForm;
import com.moa.domain.Member;
import com.moa.domain.RoleStatus;
import com.moa.dto.response.MemberDetails;
import com.moa.repository.MemberRepository;
import com.moa.service.MemberService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl implements UserDetailsService, MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EntityManager em;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        // db에서 찾은 객체가 없을 경우 null 반환
        // 있으면 MemberDetails 객체로 반환
        Optional<Member> findMember = memberRepository
                .findByName(name);

        // 비밀번호는 알아서 해줌

        return findMember
                .map(MemberDetails::new)
                .orElse(null);
    }

    @Override
    @Transactional
    public boolean join(MemberForm memberForm) {
        // 중복 회원 검증
        if (memberRepository.existsByName(memberForm.getName())) {
            return false;
        }

        Member member = new Member(
                memberForm.getName(),// 이름
                bCryptPasswordEncoder.encode(memberForm.getPassword()),// 비밀번호 암호화
                RoleStatus.USER.getAuthority() // 권한
        );
        memberRepository.save(member);
        return true;
    }

    @Override
    public Member findOne(String memberName) {
        return memberRepository.findByName(memberName).orElse(null);
    }

    @Override
    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    // 전체 유저 검색
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    // 권한 검색
    public List<Member> findAllByRole(String role) {
        return memberRepository.findAllByRole(role);
    }

    @Override
    @Transactional
    public boolean update(MemberForm memberForm) {

        // 원본 멤버 찾기
        Optional<Member> findMember = memberRepository.findById(memberForm.getId());
        //있으면 시작
        if (findMember.isPresent()) {
            Member member = findMember.get();
            // 바꾸려는 이름이 같은이름이면 메소드 종료
            //중복 검증, 바꾸려는 이름과 같은 멤버가 이미 있으면 메소드 종료, 사용자에게 중복이라고 알려주는 것은 보류
            if (member.getName().equals(memberForm.getName()) ||
                memberRepository.existsByName(memberForm.getName())) {
                return false;
            }
            // 이름 변경
            member.setName(memberForm.getName());
        }
        return true;
    }

    @Override
    @Transactional
    public boolean retire(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        member.ifPresent(findMember -> findMember.setEnabled(false));

        return member.isPresent();
    }

}
