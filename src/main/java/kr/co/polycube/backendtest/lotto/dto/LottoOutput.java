package kr.co.polycube.backendtest.lotto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LottoOutput {
    private List<Integer> numbers;
}
