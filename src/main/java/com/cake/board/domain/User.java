package com.cake.board.domain;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userid;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(length = 15)
    private String password;

    @Column(nullable = false, unique = true)
    private Long phoneNumber;


    // 회원정보 수정
    public void modify(String username, String password, Long phoneNumber) {
        this.username= username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

}
