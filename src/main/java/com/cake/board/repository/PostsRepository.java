package com.cake.board.repository;

import com.cake.board.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    List<Posts> findAllByOrderByModifiedDateDesc();
    /* findAll: 모든 엔티티를 조회한다
       By: 조회 조건을 지정한다. 수정 시간을 기준으로 조회함
       OrderBy: 결과를 정렬한다 수정 시간을 기준으로 내림차순으로
       ModifiedAt: 수정 시간에 대한 조건
       Desc: 내림차순으로 정렬
     */
    List<Posts> findByTitleOrderByModifiedDateDesc(String title);
    List<Posts> findByWriterOrderByModifiedDateDesc(String writer);
}