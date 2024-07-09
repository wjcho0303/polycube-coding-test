package kr.co.polycube.backendtest.user.service;

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

    // TODO: 예외 처리
    public UserResponse getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserResponse(user.getId(), user.getName());
        }
        return null;
    }

    // TODO: 예외 처리
    public UserResponse updateUser(Long id, String name) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(name);

            User updatedUser = userRepository.save(user);
            return new UserResponse(updatedUser.getId(), updatedUser.getName());
        }
        return null;
    }

}
