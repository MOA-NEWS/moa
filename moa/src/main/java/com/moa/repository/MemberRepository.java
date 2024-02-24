package com.moa.repository;

import com.moa.domain.Member;
import com.moa.domain.RoleStatus;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    public Optional<Member> findByName(String name) {
        List<Member> m = em.createQuery("select m from Member m where name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return m.stream().findAny();

    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 모든 일반유저 검색
    public List<Member> findAllMembers() {
        return em.createQuery("SELECT m FROM Member m WHERE m.role = :role", Member.class)
                .setParameter("role", RoleStatus.USER)
                .getResultList();
    }

    public void deleteById(Long memberId) {
        Optional<Member> findMember = findById(memberId);
        findMember.ifPresent(em::remove);
    }
}
