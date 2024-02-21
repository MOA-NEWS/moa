package com.moa.service;

import com.moa.domain.Board;
import com.moa.domain.Member;
import com.moa.domain.Comment;
import com.moa.domain.RoleStatus;
import com.moa.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
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
