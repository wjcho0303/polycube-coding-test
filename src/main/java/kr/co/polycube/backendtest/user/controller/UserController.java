package kr.co.polycube.backendtest.user.controller;

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

    //TODO: HttpStatus.NOT_FOUND 처리 후에 수정
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
        UserResponse response = userService.getUser(id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestParam(name = "name") String name) {
        UserResponse response = userService.addUser(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO: HttpStatus.NOT_FOUND 처리 후에 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id,
                                                   @RequestParam(name = "name") String name) {
        UserResponse response = userService.updateUser(id, name);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
