package com.moa.service;

import com.moa.domain.Board;
import com.moa.domain.BoardPreference;
import com.moa.repository.BoardPreferenceRepository;
import com.moa.repository.BoardRepository;
import com.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
public class TestService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardPreferenceRepository boardPreferenceRepository;

    @Transactional // 좋아요 / 싫어요 통합 토글
    public void beforeTogglePrefer(Long memberId, Long boardId, boolean isDislike) {
        // 게시글을 찾아보고 없으면 예외처리
        System.out.println("시작");
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 해당 회원이 해당 게시물에 선호도가 있는지 확인
        if (!boardPreferenceRepository
                .existsByMemberIdAndBoardId(memberId, boardId)) {
            // 선호도가 없으면 없다면 새로운 객체를 생성하고 저장
            BoardPreference boardPreference = BoardPreference
                    .createBoardPreference(memberRepository.findById(memberId).get(), board, !isDislike, isDislike);
            boardPreferenceRepository.save(boardPreference);
            System.out.println("끝");
        } else {
            // 선호도가 있을 경우에는 해당 객체를 가져옴
            BoardPreference boardPreference = boardPreferenceRepository.findByMemberIdAndBoardId(memberId, boardId);
            if(isDislike) {
                // 싫어요 상태를 반전 및 좋아요 초기화
                boardPreferenceRepository.updatePreferenceStatus(memberId, boardId, false, !boardPreference.isDislikes());
                System.out.println("끝");
            } else {
                // 좋아요 상태를 반전 및 싫어요 초기화
                boardPreferenceRepository.updatePreferenceStatus(memberId, boardId, !boardPreference.isLikes(), false);
                System.out.println("끝");
            }
        }
    }

    // 좋아요 누적수
    public Long beforeCountLikes(Long boardId) {
        return boardPreferenceRepository.countLikesByBoardId(boardId);
    }

    // 싫어요 누적수
    public Long beforeCountDislikes(Long boardId) {
        return boardPreferenceRepository.countDislikesByBoardId(boardId);
    }



    @Transactional // 좋아요 / 싫어요 통합 토글
    public void afterTogglePrefer(Long memberId, Long boardId, boolean isDislike) {
        System.out.println("시작");
        boardPreferenceRepository.callTogglePrefer(memberId, boardId, isDislike);
        System.out.println("끝");
    }

    // 좋아요 누적수
    public Long afterCountLikes(Long boardId) {
        return boardPreferenceRepository.callCountLikesByBoardIdProcedure(boardId);
    }

    // 싫어요 누적수
    public Long afterCountDislikes(Long boardId) {
        return boardPreferenceRepository.callCountDislikesByBoardIdProcedure(boardId);
    }
}
