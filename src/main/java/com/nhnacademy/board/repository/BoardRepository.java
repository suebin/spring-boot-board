package com.nhnacademy.board.repository;

import com.nhnacademy.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Post, Long> {
}

