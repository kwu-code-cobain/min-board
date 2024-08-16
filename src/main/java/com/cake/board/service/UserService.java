package com.cake.board.service;


import com.cake.board.domain.User;
import com.cake.board.dto.UserDto;
import com.cake.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class UserService {
    private final UserRepository userRepository;

    // 회원 가입
    @Transactional
    public Long joinUser(UserDto.Request dto) {
        User user = dto.toEntity();

        if (userRepository.existsByUserid(user.getUserid())) {
            throw new IllegalArgumentException("중복된 ID가 존재합니다.");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다");
        }

        userRepository.save(user);
        return user.getId();
    }

    // 회원 조회 - 전체
    @Transactional(readOnly = true)
    public List<UserDto.Response> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDto.Response::new)
                .collect(Collectors.toList());
    }

    // 회원 조회 - userid
    @Transactional(readOnly = true)
    public UserDto.Response findByUserid(String userid) {
        User user = userRepository.findByUserid(userid).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다. id: " + userid));

        return new UserDto.Response(user);
    }


    // 회원 조회 - username
    @Transactional(readOnly = true)
    public UserDto.Response findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 이름이 존재하지 않습니다. username: " + username));

        return new UserDto.Response(user);
    }



    // 회원 정보 수정
    @Transactional
    public void modify(UserDto.Request dto){
        User user = userRepository.findById(dto.getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");
        }

        user.modify(dto.getUsername(), dto.getPassword(), dto.getPhoneNumber());
        userRepository.save(user);
    }


    // 회원 탈퇴
    @Transactional
    public void deleteUser(String userid, String password){
        User user = userRepository.findByUserid(userid).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다. id: " + userid));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        userRepository.delete(user);
    }



    // 로그인 ?
}
