package kr.co.polycube.backendtest.user.controller;

import kr.co.polycube.backendtest.user.dto.UserAddResponse;
import kr.co.polycube.backendtest.user.dto.UserResponse;
import kr.co.polycube.backendtest.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
        UserResponse response = userService.getUser(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserAddResponse> addUser(@RequestParam(name = "name") String name) {
        UserAddResponse response = userService.addUser(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id,
                                                   @RequestParam(name = "name") String name) {
        UserResponse response = userService.updateUser(id, name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
