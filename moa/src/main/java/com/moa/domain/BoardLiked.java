package com.moa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BoardLiked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardLike_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private boolean liked;

    // 기본생성자 사용 금지
    protected BoardLiked() {
    }

    public static BoardLiked createBoardLike(Member member, Board board, boolean liked) {
        BoardLiked boardLiked = new BoardLiked();
        boardLiked.setMember(member);
        boardLiked.setBoard(board);
        boardLiked.setLiked(liked);

        return boardLiked;
    }
}
