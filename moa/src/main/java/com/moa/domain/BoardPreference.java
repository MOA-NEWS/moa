package com.moa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BoardPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_preference_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private boolean likes;
    private boolean dislikes;

    // 기본생성자 사용 금지
    protected BoardPreference() {
    }

    public static BoardPreference createBoardPreference(Member member, Board board, boolean likes, boolean dislikes) {
        BoardPreference boardPreference = new BoardPreference();
        boardPreference.setMember(member);
        boardPreference.setBoard(board);
        boardPreference.setLikes(likes);
        boardPreference.setDislikes(dislikes);

        return boardPreference;
    }
}
