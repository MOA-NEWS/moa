package com.moa.dto.response;

import com.moa.domain.Board;
import com.moa.domain.Comment;
import com.moa.domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private Member member;
    private String content;
    private LocalDateTime postDate;
    private List<Comment> comments;

    public BoardResponseDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.member = board.getMember();
        this.content = board.getContent();
        this.postDate = board.getPostDate();
        this.comments = board.getComments();
    }

    // make toString method whit StringBuilder
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BoardResponseDto{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", member=").append(member);
        sb.append(", content='").append(content).append('\'');
        sb.append(", postDate=").append(postDate);
        sb.append(", comments=").append(comments);
        sb.append('}');
        return sb.toString();
    }
}
