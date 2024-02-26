package com.moa.domain;

import com.moa.domain.Member;
import com.moa.dto.response.BoardListResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NamedStoredProcedureQuery(
        name = "findAllByPageNum",
        procedureName = "call_find_all_by_page_num",
        resultClasses = Long.class,
        parameters = {
                @StoredProcedureParameter(name = "page_num", mode = ParameterMode.IN, type = Integer.class),
                @StoredProcedureParameter(name = "page_size", mode = ParameterMode.IN, type = Integer.class),
                @StoredProcedureParameter(name = "boardId", mode = ParameterMode.OUT, type = Long.class),

        }
)
@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Length(min = 1, max = 30)
    private String title;

    private String content;

    private LocalDateTime postDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardPreference> boardPreferences = new ArrayList<>();

    // 기본생성자 사용 금지
    protected Board() {
    }

    public Board(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    // 양방향으로 참조
    public void setComment(Comment comment) {
        this.comments.add(comment);
        comment.setBoard(this);
    }
}