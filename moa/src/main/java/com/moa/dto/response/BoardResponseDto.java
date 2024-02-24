package com.moa.dto.response;

import com.moa.domain.Board;
import com.moa.domain.Comment;
import com.moa.domain.old.Member;
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

}
