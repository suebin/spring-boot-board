package com.nhnacademy.board.board.service;

import com.nhnacademy.board.board.domain.PostRequest;
import com.nhnacademy.board.entity.Post;
import com.nhnacademy.board.exception.PostNotFoundException;
import com.nhnacademy.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Post getPost(Long id){
        Post post = boardRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
        return post;
    }

    public Page<Post> getPostList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    public void register(PostRequest postRequest) {
        Post post = new Post(postRequest.getTitle(),postRequest.getContent(), postRequest.getWriterUserId());
        boardRepository.save(post);
    }

    public void update(PostRequest postRequest){
        Post post = boardRepository.findById(postRequest.getId()).orElseThrow(() -> new PostNotFoundException());
        post.update(postRequest.getTitle(), postRequest.getContent());
    }

    public void delete(Long id){
        boardRepository.deleteById(id);
    }

}
