package com.cake.board.controller;
import com.cake.board.domain.User;
import com.cake.board.dto.UserDto;
import com.cake.board.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@RestController

public class UserController {
    private final UserService userService;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<Long> joinUser(@RequestBody UserDto.Request dto) {
        return ResponseEntity.ok(userService.joinUser(dto));

    }

    // 회원 조회 - 전체
    @GetMapping("/signup")
    public ResponseEntity<List<UserDto.Response>> getAllUsers() {
        List<UserDto.Response> user = userService.getAllUsers();
        return ResponseEntity.ok(user);
    }

    // 회원 조회 - userid
    @GetMapping("/info/userid/{userid}")
    public ResponseEntity<UserDto.Response> getUserid(@PathVariable String userid) {
        return ResponseEntity.ok(userService.findByUserid(userid));
    }

    // 회원 조회 - username
    @GetMapping("/info/username/{username}")
    public ResponseEntity<UserDto.Response> getUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    // 회원 정보 수정
    @PutMapping("/edit/{userid}")
    public ResponseEntity<String> modify(@PathVariable String userid, @RequestBody UserDto.Request dto) {
        try {
            dto.setUserid(userid);
            userService.modify(dto);
            return ResponseEntity.ok("회원정보가 성공적으로 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 회원 탈퇴
    @DeleteMapping("/edit/{userid}/{password}")
    public ResponseEntity<String> deleteUser(@PathVariable String userid, @PathVariable String password) {
        try {
            userService.deleteUser(userid, password);
            return ResponseEntity.ok("회원이 성공적으로 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // 로그인
    @GetMapping("/login/{userid}/{password}")
    public ResponseEntity<String> validateUser(@PathVariable String userid, @PathVariable String password) {
        boolean isValid = userService.validateUser(userid, password);

        if (isValid) {
            return ResponseEntity.ok("로그인 성공하였습니다");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

    }
}
