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
@Transactional(readOnly = true)
public class BoardPreferenceService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardPreferenceRepository boardPreferenceRepository;

    @Transactional // 좋아요 토글
    public void toggleLike(Long memberId, Long boardId) {
        // 게시글을 찾아보고 없으면 예외처리
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 해당 회원이 해당 게시물에 선호도가 있는지 확인
        if (!boardPreferenceRepository.existsByMemberIdAndBoardId(memberId, boardId)) {
            // 선호도가 없으면 없다면 새로운 객체를 생성하고 저장
            BoardPreference boardPreference = BoardPreference
                    .createBoardPreference(memberRepository.findById(memberId).get(), board, true, false);
            boardPreferenceRepository.save(boardPreference);
        } else {
            // 선호도가 있을 경우에는 해당 객체를 가져옴
            BoardPreference boardPreference = boardPreferenceRepository.findByMemberIdAndBoardId(memberId, boardId);
            // 좋아요 상태를 반전 및 싫어요 초기화
            boardPreferenceRepository.updatePreferenceStatus(memberId, boardId, !boardPreference.isLikes(), false);
        }
    }

    @Transactional // 싫어요 토글
    public void toggleDislike(Long memberId, Long boardId) {
        // 게시글을 찾아보고 없으면 예외처리
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 해당 회원이 해당 게시물에 선호도가 있는지 확인
        if (!boardPreferenceRepository.existsByMemberIdAndBoardId(memberId, boardId)) {
            // 선호도가 없으면 없다면 새로운 객체를 생성하고 저장
            BoardPreference boardPreference = BoardPreference
                    .createBoardPreference(memberRepository.findById(memberId).get(), board, false, true);
            boardPreferenceRepository.save(boardPreference);
        } else {
            // 선호도가 있을 경우에는 해당 객체를 가져옴
            BoardPreference boardPreference = boardPreferenceRepository.findByMemberIdAndBoardId(memberId, boardId);
            // 싫어요 상태를 반전 및 좋아요 초기화
            boardPreferenceRepository.updatePreferenceStatus(memberId, boardId, false, !boardPreference.isDislikes());
        }
    }

    @Transactional //좋아요 / 싫어요 토글
    public void togglePrefer(Long memberId, Long boardId, boolean isDislike) {
        // 게시글을 찾아보고 없으면 예외처리
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 해당 회원이 해당 게시물에 선호도가 있는지 확인
        if (!boardPreferenceRepository.existsByMemberIdAndBoardId(memberId, boardId)) {
            // 선호도가 없으면 없다면 새로운 객체를 생성하고 저장
            BoardPreference boardPreference = BoardPreference
                    .createBoardPreference(memberRepository.findById(memberId).get(), board, !isDislike, isDislike);
            boardPreferenceRepository.save(boardPreference);
        } else {
            // 선호도가 있을 경우에는 해당 객체를 가져옴
            BoardPreference boardPreference = boardPreferenceRepository.findByMemberIdAndBoardId(memberId, boardId);
            if (isDislike)
                // 싫어요 상태를 반전 및 좋아요 초기화
                boardPreferenceRepository.updatePreferenceStatus(memberId, boardId, false, !boardPreference.isDislikes());
            else
                // 좋아요 상태를 반전 및 싫어요 초기화
                boardPreferenceRepository.updatePreferenceStatus(memberId, boardId, !boardPreference.isLikes(), false);
        }
    }

    // 좋아요 누적수
    public Long countLikes(Long boardId) {
        return boardPreferenceRepository.countLikesByBoardId(boardId);
    }

    // 싫어요 누적수
    public Long countDislikes(Long boardId) {
        return boardPreferenceRepository.countDislikesByBoardId(boardId);
    }
}
