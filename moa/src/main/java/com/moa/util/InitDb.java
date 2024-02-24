package com.moa.util;

import com.moa.domain.Board;
import com.moa.domain.old.Member;
import com.moa.domain.Comment;
import com.moa.domain.RoleStatus;
import com.moa.service.impl.MemberService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
        initService.dbInit3();
        initService.dbInit4();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        private final MemberService memberService;
        public void dbInit1() {
            Member member = createMember("userA", RoleStatus.ADMIN);
            em.persist(member);

            Member findMember = memberService.findOne("userA");
            Board book1 = createBoard("1st", "첫번째", findMember);
            em.persist(book1);

            Board book2 = createBoard("2nd", "두번째", findMember);
            em.persist(book2);
        }

        public void dbInit2() {
            Member member = createMember("userB", RoleStatus.USER);
            em.persist(member);

            Member findMember = memberService.findOne("userB");
            Board book1 = createBoard("3rd", "세번째", findMember);
            em.persist(book1);

            Board book2 = createBoard("4th", "네번째", findMember);
            em.persist(book2);
        }

        public void dbInit3() {
            Member member = createMember("userC", RoleStatus.USER);
            em.persist(member);
        }

        public void dbInit4() {
            Member member = createMember("userD", RoleStatus.USER);
            em.persist(member);

            Member findMember = memberService.findOne("userD");
            Board book1 = createBoard("Gear 5", "동물계 환수종 사람사람 열매 모델 니카", findMember);
            em.persist(book1);
        }

        // 생성자 메소드
        private static Member createMember(String name, RoleStatus role) {
            return new Member(name, role);
        }

        private static Board createBoard(String title, String content, Member member) {
            return new Board(title, content, member);
        }

        private static Comment createComment(String content, Member member, Board board) {
            return Comment.createComment(content, member, board);
        }
    }
}
