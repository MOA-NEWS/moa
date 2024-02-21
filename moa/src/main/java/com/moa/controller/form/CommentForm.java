package com.moa.controller.form;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentForm {
    private Long parentId;
    private String text;
    private LocalDateTime commentDate;
}
