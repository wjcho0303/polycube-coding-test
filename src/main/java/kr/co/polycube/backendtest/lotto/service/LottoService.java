package kr.co.polycube.backendtest.lotto.service;

import kr.co.polycube.backendtest.lotto.dto.LottoOutput;
import kr.co.polycube.backendtest.lotto.entity.Lotto;
import kr.co.polycube.backendtest.lotto.repository.LottoRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LottoService {

    private final LottoRespository lottoRepository;

    public LottoOutput generateLottoNumbers() {
        Set<Integer> numbers  = new HashSet<>();
        while (numbers.size() < 6) {
            int number = (int) (Math.random() * 45) + 1;
            numbers.add(number);
        }

        List<Integer> lottoNumbers = new ArrayList<>(numbers);
        Lotto lotto = new Lotto();
        lotto.setNumber1(lottoNumbers.get(0));
        lotto.setNumber2(lottoNumbers.get(1));
        lotto.setNumber3(lottoNumbers.get(2));
        lotto.setNumber4(lottoNumbers.get(3));
        lotto.setNumber5(lottoNumbers.get(4));
        lotto.setNumber6(lottoNumbers.get(5));

        lottoRepository.save(lotto);
        return new LottoOutput(lottoNumbers);
    }
}
