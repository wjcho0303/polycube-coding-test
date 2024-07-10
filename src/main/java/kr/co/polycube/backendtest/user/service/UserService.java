package kr.co.polycube.backendtest.user.service;

import kr.co.polycube.backendtest.global.exception.model.NotFoundException;
import kr.co.polycube.backendtest.user.dto.UserAddResponse;
import kr.co.polycube.backendtest.user.dto.UserResponse;
import kr.co.polycube.backendtest.user.entity.User;
import kr.co.polycube.backendtest.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserAddResponse addUser(String name) {
        User user = new User(name);
        User savedUser = userRepository.save(user);
        return new UserAddResponse(savedUser.getId());
    }

    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("유저를 찾을 수 없습니다."));

        return new UserResponse(user.getId(), user.getName());
    }

    public UserResponse updateUser(Long id, String name) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("유저를 찾을 수 없습니다."));

        user.setName(name);
        User updatedUser = userRepository.save(user);

        return new UserResponse(updatedUser.getId(), updatedUser.getName());
    }

}
