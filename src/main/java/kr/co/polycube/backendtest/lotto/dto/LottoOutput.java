package kr.co.polycube.backendtest.lotto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class LottoOutput {
    private List<Integer> numbers;
}
