package com.cake.board.service;

import com.cake.board.domain.Posts;
import com.cake.board.dto.PostsDto;
import com.cake.board.repository.PostsRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    // 게시글 생성
    @Transactional
    public Long save(PostsDto.Request dto) {
        Posts posts = dto.toEntity();
        postsRepository.save(posts);
        return posts.getId();
    }

    // 게시글 조회 - 전체
    @Transactional(readOnly = true)
    public List<PostsDto.Response> getAllPosts() {
        List<Posts> posts = postsRepository.findAllByOrderByModifiedDateDesc();
        return posts.stream()
                .map(PostsDto.Response::new)
                .collect(Collectors.toList());
    }

    // 게시글 조회 - 특정 id
    @Transactional(readOnly = true)
    public PostsDto.Response findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        return new PostsDto.Response(posts);
    }

    // 게시글 조회 - 특정 제목
    @Transactional(readOnly = true)
    public List<PostsDto.Response> findByTitle(String title) {
        List<Posts> posts = postsRepository.findByTitleOrderByModifiedDateDesc(title);

        //예외처리
        if (posts.isEmpty()) {
            throw new IllegalArgumentException("해당 제목의 게시글이 존재하지 않습니다. title: " + title);
        }

        return posts.stream()
                .map(PostsDto.Response::new)
                .collect(Collectors.toList());


    }

    // 게시글 조회 - 특정 글쓴이
    @Transactional(readOnly = true)
    public List<PostsDto.Response> findByWriter(String writer) {
        List<Posts> posts = postsRepository.findByWriterOrderByModifiedDateDesc(writer);

        //예외처리
        if (posts.isEmpty()) {
            throw new IllegalArgumentException("해당 작성자의 게시글이 존재하지 않습니다. writer: " + writer);
        }

        return posts.stream()
                .map(PostsDto.Response::new)
                .collect(Collectors.toList());
    }

    // 게시글 수정
    @Transactional
    public void update(Long id, PostsDto.Request dto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        posts.update(dto.getTitle(), dto.getContent());
    }

    // 게시글 삭제
    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        postsRepository.delete(posts);
    }
}



