package kr.co.polycube.backendtest.lottotest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.lotto.dto.LottoOutput;
import kr.co.polycube.backendtest.lotto.entity.Lotto;
import kr.co.polycube.backendtest.lotto.repository.LottoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class LottoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LottoRepository lottoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        lottoRepository.deleteAll();
    }

    // 로또 번호 생성 테스트
    @Test
    @DisplayName("로또 번호 생성")
    public void testGenerateLottoNumbers() throws Exception {
        MvcResult result = mockMvc.perform(post("/lottos"))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        LottoOutput lottoOutput = objectMapper.readValue(responseContent, LottoOutput.class);

        List<Integer> numbers = lottoOutput.getNumbers();

        assertThat(numbers).isNotNull();
        assertThat(numbers).hasSize(6);
        assertThat(numbers).allMatch(number -> number >= 1 && number <= 45);

        // 데이터베이스에 로또 번호가 저장되었는지 확인
        List<Lotto> lottos = lottoRepository.findAll();
        assertThat(lottos).hasSize(1);

        Lotto savedLotto = lottos.get(0);
        assertThat(savedLotto.getNumber1()).isEqualTo(numbers.get(0));
        assertThat(savedLotto.getNumber2()).isEqualTo(numbers.get(1));
        assertThat(savedLotto.getNumber3()).isEqualTo(numbers.get(2));
        assertThat(savedLotto.getNumber4()).isEqualTo(numbers.get(3));
        assertThat(savedLotto.getNumber5()).isEqualTo(numbers.get(4));
        assertThat(savedLotto.getNumber6()).isEqualTo(numbers.get(5));
    }
}
