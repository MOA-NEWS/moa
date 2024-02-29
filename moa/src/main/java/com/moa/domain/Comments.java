package com.moa.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NamedStoredProcedureQuery(
        name = "addComment",
        procedureName = "call_add_comment",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "pi_parent_id", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "pi_member_id", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "pi_content", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "pi_board_id", type = Long.class),
//                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "po_temp", type = Long.class)
        }
)
@Entity
@Getter
@Setter
public class Comments {

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
    private  Comments parent;

    //일대다
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> children = new ArrayList<>();

    public static Comments createComment(String content, Member member, Board board) {
        Comments comment = new Comments();
        comment.setMember(member);
        comment.setBoard(board);
        comment.setContent(content);
        comment.setCommentDate(LocalDateTime.now());

        return comment;
    }

    public void setParent(Comments parent) {
        this.parent = parent;
        this.children.add(this);
    }

    // 기본생성자 사용 금지
    protected Comments() {
    }

    @Override
    public String toString() {
        return "Comments{" +
               "id=" + id +
               ", content='" + content + '\'' +
               ", commentDate=" + commentDate +
               ", member=" + member +
               ", board=" + board +
               ", parent=" + parent +
               ", children=" + children +
               '}';
    }
}
