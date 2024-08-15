package com.cake.board.dto;

import com.cake.board.domain.Posts;
import lombok.*;

 //request, response DTO 클래스를 하나로 묶어서 InnerStaticClass로 한 번에 관리

public class PostsDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private Long id;
        private String title;
        private String writer;
        private String content;
        private String createdDate, modifiedDate;

        // Dto -> Entity
        public Posts toEntity() {
            Posts posts = Posts.builder()
                    .id(id)
                    .title(title)
                    .writer(writer)
                    .content(content)
                    .build();

            return posts;
        }
    }

    @Getter
    public static class Response {
        private final Long id;
        private final String title;
        private final String writer;
        private final String content;
        private final String createdDate, modifiedDate;

        // Entity -> Dto
        public Response(Posts posts) {
            this.id = posts.getId();
            this.title = posts.getTitle();
            this.writer = posts.getWriter();
            this.content = posts.getContent();
            this.createdDate = posts.getCreatedDate();
            this.modifiedDate = posts.getModifiedDate();
        }
    }
}