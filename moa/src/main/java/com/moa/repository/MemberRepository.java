package com.moa.repository;

import com.moa.domain.Member;
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

    // 전체 유저 검색
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 권한 검색
    public List<Member> findAllByRole(String role) {
        return em.createQuery("SELECT m FROM Member m WHERE m.role like concat ('ROLE_',:role)", Member.class)
                .setParameter("role", role.toUpperCase())
                .getResultList();
    }

    public boolean existsByName(String name) {
        return findByName(name).isPresent();
    }
    // Member.locked 업데이트로 바꿔야함
    public void deleteById(Long memberId) {
//        Optional<Member> findMember = findById(memberId);
//        findMember.ifPresent(em::remove);
    }
}
