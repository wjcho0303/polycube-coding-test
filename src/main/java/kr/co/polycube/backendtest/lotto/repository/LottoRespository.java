package kr.co.polycube.backendtest.lotto.repository;

import kr.co.polycube.backendtest.lotto.entity.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LottoRespository extends JpaRepository<Lotto, Long> {
}
