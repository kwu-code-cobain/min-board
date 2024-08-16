package com.cake.board.controller;

import com.cake.board.dto.PostsDto;
import com.cake.board.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    // 게시글 생성
    @PostMapping("/posts")
    public ResponseEntity<Long> save(@RequestBody PostsDto.Request dto) {
        return ResponseEntity.ok(postsService.save(dto));
    }

    // 게시글 조회 - 전체
    @GetMapping("/posts")
    public ResponseEntity<List<PostsDto.Response>> getAllPosts() {
        List<PostsDto.Response> posts = postsService.getAllPosts();
        return ResponseEntity.ok(posts);
    }


    // 게시글 조회 - 특정 id
    @GetMapping("/posts/id/{id}")
    public ResponseEntity<PostsDto.Response> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postsService.findById(id));
    }

    // 게시글 조회 - 특정 제목
    @GetMapping("/posts/title/{title}")
    public ResponseEntity<List<PostsDto.Response>> getPostsByTitle(@PathVariable String title) {
        return ResponseEntity.ok(postsService.findByTitle(title));
    }

    // 게시글 조회 - 특정 글쓴이
    @GetMapping("/posts/writer/{writer}")
    public ResponseEntity<List<PostsDto.Response>> getPostsByWriter(@PathVariable String writer) {
        return ResponseEntity.ok(postsService.findByWriter(writer));
    }
    // 게시글 수정
    @PutMapping("/posts/update/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody PostsDto.Request dto) {
        postsService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    // 게시글 삭제
    @DeleteMapping("/posts/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        postsService.delete(id);
        return ResponseEntity.ok(id);
    }

}
