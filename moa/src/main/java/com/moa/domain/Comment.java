package com.moa.domain;

import com.moa.domain.old.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private LocalDateTime commentDate;

    //다대일
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //다대일
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    //일대다
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();

    public static Comment createComment(String content, Member member, Board board) {
        Comment comment = new Comment();
        comment.setMember(member);
        comment.setBoard(board);
        comment.setContent(content);
        comment.setCommentDate(LocalDateTime.now());

        return comment;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
        this.children.add(this);
    }

    // 기본생성자 사용 금지
    protected Comment() {
    }


}
