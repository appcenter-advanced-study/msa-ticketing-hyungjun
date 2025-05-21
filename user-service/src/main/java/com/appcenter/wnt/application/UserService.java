package com.appcenter.wnt.application;

import com.appcenter.wnt.application.dto.request.UpdateNicknameRequest;
import com.appcenter.wnt.domain.User;
import com.appcenter.wnt.application.dto.request.CreateUserRequest;
import com.appcenter.wnt.application.dto.response.UserResponse;
import com.appcenter.wnt.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("유저가 존재하지 않습니다."));
        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse register(CreateUserRequest userRequest) {
        User user = User.of(userRequest.nickname());
        userRepository.save(user);
        return UserResponse.from(user);
    }

    @Transactional
    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("유저가 존재하지 않습니다."));
        userRepository.delete(user);
    }

    @Transactional
    public UserResponse updateUser(Long userId, UpdateNicknameRequest updateNicknameRequest) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("유저가 존재하지 않습니다."));
        user.updateNickname(updateNicknameRequest.nickname());
        return UserResponse.from(user);
    }

}
