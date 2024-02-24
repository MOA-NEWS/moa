package com.moa.repository;

import com.moa.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Board, Long> {

}