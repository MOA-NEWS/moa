package com.moa.service;

import com.moa.domain.Board;
import com.moa.domain.BoardLiked;
import com.moa.repository.BoardLikedRepository;
import com.moa.repository.BoardRepository;
import com.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardLikedService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardLikedRepository boardLikedRepository;

    @Transactional // 좋아요토글
    public void toggleLike(Long memberId, Long boardId) {
        // 게시글을 찾아보고 없으면 예외처리
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 해당 회원이 해당 게시물에 좋아요를 이미 눌렀는지 확인
        if (!boardLikedRepository.existsByMemberIdAndBoardId(memberId, boardId)) {
            // 좋아요를 누른 적이 없다면 새로운 좋아요 엔티티를 생성하고 저장
            BoardLiked boardLiked = BoardLiked.createBoardLike(memberRepository.findById(memberId).get(), board, true);
            boardLikedRepository.save(boardLiked);
        } else {
            // 이미 좋아요를 누른 경우에는 해당 좋아요 엔티티를 찾습니다.
            BoardLiked boardLiked = boardLikedRepository.findByMemberIdAndBoardId(memberId, boardId);
            // 좋아요 상태를 반전.
            boardLikedRepository.updateLikedStatus(memberId, boardId, !boardLiked.isLiked());
        }
    }

    // 좋아요 누적수
    public Long countLikes(Long boardId) {
        return boardLikedRepository.countLikesByBoardId(boardId);
    }
}
