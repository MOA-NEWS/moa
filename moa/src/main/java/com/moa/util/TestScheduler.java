package com.moa.util;

import com.moa.controller.form.CommentForm;
import com.moa.service.impl.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestScheduler {
    private final CommentService commentsService;

    static CommentForm form = new CommentForm(){{
        setParentId(null);
        setContent("테스트 댓글");

    }};
    //https://velog.io/@developer_khj/Spring-Boot-Scheduler-Scheduled (참고 사이트)
    //MoaApplication({프로젝트이름}Application)에서 @EnableScheduling 어노테이션 추가
    //스케줄러 사용 할 클래스 생성하여 @Component 어노테이션 추가
    //스케줄러 사용 할 메소드에 @Scheduled 어노테이션 추가
    //주기적으로 반복하고 싶은 내용 작성


//    @Scheduled(fixedDelay = 1000, initialDelay = 3000)
    public void runSp() {
        commentsService.spAddComment(2L, 1L, form);
    }

//    @Scheduled(fixedDelay = 1000, initialDelay = 3000)
    public void runJap() {
        commentsService.addComment(2L, 1L, form);
    }
}
