package com.cake.board.dto;

import com.cake.board.domain.User;
import lombok.*;

public class UserDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long id;
        private String userid;
        private String username;
        private String password;
        private Long phoneNumber;

        // Dto -> Entity
        public User toEntity() {
            User user = User.builder()
                    .id(id)
                    .userid(userid)
                    .username(username)
                    .password(password)
                    .phoneNumber(phoneNumber)
                    .build();
            return user;
        }
    }

    @Getter
    public static class Response {

        private final Long id;
        private final String userid;
        private final String username;
        private final String password;
        private final Long phoneNumber;


        // Entity -> dto
        public Response(User user) {
            this.id = user.getId();
            this.userid = user.getUserid();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.phoneNumber = user.getPhoneNumber();
        }
    }
}
