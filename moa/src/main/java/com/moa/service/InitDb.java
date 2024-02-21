//package com.moa.service;
//
//import com.moa.domain.Board;
//import com.moa.domain.Member;
//import com.moa.domain.Comment;
//import com.moa.domain.RoleStatus;
//import jakarta.annotation.PostConstruct;
//import jakarta.persistence.EntityManager;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.awt.print.Book;
//
//@Component
//@RequiredArgsConstructor
//public class InitDb {
//
//    private final InitService initService;
//
//    @PostConstruct
//    public void init() {
//        initService.dbInit1();
//        initService.dbInit2();
//    }
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService {
//        private final EntityManager em;
//
//        public void dbInit1() {
//            Member member = createMember("userA", "seoul", "1", "1111");
//            em.persist(member);
//
//            Book book1 = createBook("JPA1 BOOK", 10000, 100);
//            em.persist(book1);
//
//            Book book2 = createBook("JPA2 BOOK", 20000, 200);
//            em.persist(book2);
//
//            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
//            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
//
//            Delivery delivery = createDelivery(member);
//            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
//            em.persist(order);
//        }
//
//
//        public void dbInit2() {
//            Member member = createMember("userB", "busan", "2", "2222");
//            em.persist(member);
//
//            Book book1 = createBook("SPRING1 BOOK", 30000, 300);
//            em.persist(book1);
//
//            Book book2 = createBook("SPRING2 BOOK", 40000, 400);
//            em.persist(book2);
//
//            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 30000, 3);
//            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);
//
//            Delivery delivery = createDelivery(member);
//            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
//            em.persist(order);
//        }
//
//        // 생성자 메소드
//        private static Member createMember(String name, RoleStatus role) {
//            return new Member(name, role);
//        }
//
//        private static Board createBoard(String title, String content, Member member) {
//            return new Board(title, content, member);
//        }
//
//        private static Comment createComment(String content, Member member, Board board) {
//            return Comment.createComment(content, member, board);
//        }
//    }
//}
