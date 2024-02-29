package com.moa.util;

import com.moa.domain.Board;
import com.moa.domain.Comments;
import com.moa.domain.Member;
import com.moa.domain.RoleStatus;
import com.moa.service.MemberService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {
//        initService.dbInit1();
//        initService.dbInit2();
//        initService.dbInit3();
//        initService.dbInit4();
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
            Board book1 = createBoard("페이징 확인", "ㅇㅇ", findMember);
            em.persist(book1);
            Board book2 = createBoard("페이징 확인", "ㅇㅇ", findMember);
            em.persist(book2);
            Board book3 = createBoard("페이징 확인", "ㅇㅇ", findMember);
            em.persist(book3);
            Board book4 = createBoard("페이징 확인", "ㅇㅇ", findMember);
            em.persist(book4);
            Board book5 = createBoard("페이징 확인", "ㅇㅇ", findMember);
            em.persist(book5);
            Board book6 = createBoard("페이징 확인", "ㅇㅇ", findMember);
            em.persist(book6);
            Board book7 = createBoard("페이징 확인", "ㅇㅇ", findMember);
            em.persist(book7);
            Board book8 = createBoard("페이징 확인", "ㅇㅇ", findMember);
            em.persist(book8);
            Board book9 = createBoard("페이징 확인", "ㅇㅇ", findMember);
            em.persist(book9);
        }

        // 생성자 메소드
        private static Member createMember(String name, RoleStatus role) {
            return new Member(name, bCryptPasswordEncoder.encode("1234"), role.getAuthority());
        }

        private static Board createBoard(String title, String content, Member member) {
            return new Board(title, content, member);
        }

        private static Comments createComment(String content, Member member, Board board) {
            return Comments.createComment(content, member, board);
        }
    }
}
