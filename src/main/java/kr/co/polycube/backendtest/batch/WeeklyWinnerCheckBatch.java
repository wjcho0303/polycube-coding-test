package kr.co.polycube.backendtest.batch;

import kr.co.polycube.backendtest.lotto.entity.Lotto;
import kr.co.polycube.backendtest.lotto.repository.LottoRepository;
import kr.co.polycube.backendtest.winner.entity.Winner;
import kr.co.polycube.backendtest.winner.repository.WinnerRepository;
import kr.co.polycube.backendtest.lotto.service.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedHashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WeeklyWinnerCheckBatch {

    private final LottoRepository lottoRepository;
    private final WinnerRepository winnerRepository;
    private final LottoService lottoService;

    @Scheduled(cron = "0 0 0 * * SUN") // 매주 일요일 0시에 실행
    public void checkWinners() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sunday = now.with(
                        TemporalAdjusters.previous(DayOfWeek.SUNDAY))
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        // 지난 일주일 동안의 로또 가져오기
        List<Lotto> lottos = lottoRepository.findAllByCreatedAtBetween(sunday.minusDays(7), sunday);

        List<Integer> winningNumbers = lottoService.getWinningNumbers();

        for (Lotto lotto : lottos) {
            LinkedHashSet<Integer> userNumbers = new LinkedHashSet<>();
            userNumbers.add(lotto.getNumber1());
            userNumbers.add(lotto.getNumber2());
            userNumbers.add(lotto.getNumber3());
            userNumbers.add(lotto.getNumber4());
            userNumbers.add(lotto.getNumber5());
            userNumbers.add(lotto.getNumber6());

            // 이후 로직은 동일하게 적용됩니다
            int matchCount = (int) userNumbers.stream().filter(winningNumbers::contains).count();
            int rank = calculateRank(matchCount);
            if (rank <= 5) { // 5등까지 저장
                Winner winner = new Winner(lotto, rank);
                winnerRepository.save(winner);
            }
        }
    }

    private int calculateRank(int matchCount) {
        switch (matchCount) {
            case 6: return 1; // 6개 일치
            case 5: return 2; // 5개 일치
            case 4: return 3; // 4개 일치
            case 3: return 4; // 3개 일치
            case 2: return 5; // 2개 일치
            default: return 6; // 2개 이하 일치
        }
    }
}