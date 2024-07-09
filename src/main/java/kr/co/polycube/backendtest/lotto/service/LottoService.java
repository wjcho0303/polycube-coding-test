package kr.co.polycube.backendtest.lotto.service;

import kr.co.polycube.backendtest.lotto.dto.LottoOutput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LottoService {

    public LottoOutput generateLottoNumbers() {
        Set<Integer> numbers  = new HashSet<>();
        while (numbers.size() < 6) {
            int number = (int) (Math.random() * 45) + 1;
            numbers.add(number);
        }

        List<Integer> lottoNumbers = new ArrayList<>(numbers);
        return new LottoOutput(lottoNumbers);
    }
}
