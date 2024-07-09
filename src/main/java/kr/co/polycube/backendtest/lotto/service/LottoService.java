package kr.co.polycube.backendtest.lotto.service;

import kr.co.polycube.backendtest.lotto.dto.LottoOutput;
import kr.co.polycube.backendtest.lotto.entity.Lotto;
import kr.co.polycube.backendtest.lotto.repository.LottoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LottoService {

    private final LottoRepository lottoRepository;
    private List<Integer> winningNumbers;

    // 당첨 로또번호 생성
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

    // 당첨 번호 가져오기
    public List<Integer> getWinningNumbers() {
        List<Lotto> allLottos = lottoRepository.findAll();
        if (allLottos.isEmpty()) {
            return Collections.emptyList();
        }

        // 가장 최근에 생성된 로또 번호를 당첨 번호로 간주
        Lotto winningLotto = allLottos.get(allLottos.size() - 1);
        return Arrays.asList(
                winningLotto.getNumber1(),
                winningLotto.getNumber2(),
                winningLotto.getNumber3(),
                winningLotto.getNumber4(),
                winningLotto.getNumber5(),
                winningLotto.getNumber6()
        );
    }
}
