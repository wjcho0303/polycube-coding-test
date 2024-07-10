package kr.co.polycube.backendtest.usertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.user.dto.UserAddResponse;
import kr.co.polycube.backendtest.user.entity.User;
import kr.co.polycube.backendtest.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    // 테스트마다 미리 유저 저장
    @BeforeEach
    public void setUp() {
        testUser = new User("TestUser");
        userRepository.save(testUser);
    }

    // 특정 id PK 값 입력을 통한 유저 조회 테스트
    @Test
    @DisplayName("유저 조회")
    public void testGetUser() throws Exception {
        mockMvc.perform(get("/users/{id}", testUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testUser.getId()))
                .andExpect(jsonPath("$.name").value(testUser.getName()));
    }

    // 유저 등록 테스트
    @Test
    @DisplayName("유저 등록")
    public void testAddUser() throws Exception {
        String name = "NewUser";

        MvcResult result = mockMvc.perform(post("/users")
                        .param("name", name))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        UserAddResponse response = objectMapper.readValue(responseContent, UserAddResponse.class);

        User addedUser = userRepository.findById(response.getId()).orElse(null);

        assertThat(addedUser).isNotNull();
        assertThat(addedUser.getName()).isEqualTo(name);
    }

    // 특정 id PK 값과 수정할 이름 입력을 통한 유저 정보 수정 테스트
    @Test
    @DisplayName("유저 수정")
    public void testUpdateUser() throws Exception {
        String newName = "UpdatedUser";

        mockMvc.perform(patch("/users/{id}", testUser.getId())
                        .param("name", newName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testUser.getId()))
                .andExpect(jsonPath("$.name").value(newName));

        User updatedUser = userRepository.findById(testUser.getId()).orElse(null);

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getName()).isEqualTo(newName);
    }

    // 존재하지 않는 id PK 값 입력 시 404 Not Found 응답 테스트 - getUser
    @Test
    @DisplayName("유저 조회 404")
    public void testGetUserNotFound() throws Exception {
        mockMvc.perform(get("/users/{id}", 999999999999999L))
                .andExpect(status().isNotFound());
    }

    // 존재하지 않는 id PK 값 입력 시 404 Not Found 응답 테스트 - updateUser
    @Test
    @DisplayName("유저 수정 404")
    public void testUpdateUserNotFound() throws Exception {
        mockMvc.perform(patch("/users/{id}", 999999999999999L)
                        .param("name", "userTest"))
                .andExpect(status().isNotFound());
    }

    // 특수문자 포함 테스트 1 - 유효하지 않은 name 유저 등록 테스트
    @Test
    @DisplayName("유저 등록 특수문자 400")
    public void testAddUserWithInvalidCharactersInQueryString() throws Exception {
        mockMvc.perform(post("/users", testUser.getId())
                        .queryParam("name", "test!!"))
                .andExpect(status().isBadRequest());
    }

    // 특수문자 포함 테스트 2 - 수정할 이름이 유효하지 않은 유저 수정 테스트
    @Test
    @DisplayName("유저 수정 특수문자 400")
    public void testPatchUserWithInvalidCharacters2() throws Exception {
        mockMvc.perform(patch("/users/{id}", testUser.getId())
                        .queryParam("name", "badName!!"))
                .andExpect(status().isBadRequest());
    }
}