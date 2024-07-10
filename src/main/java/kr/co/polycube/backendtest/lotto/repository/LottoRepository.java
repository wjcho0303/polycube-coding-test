package kr.co.polycube.backendtest.lotto.repository;

import kr.co.polycube.backendtest.lotto.entity.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LottoRepository extends JpaRepository<Lotto, Long> {
    @Query("SELECT l FROM Lotto l WHERE l.createdAt BETWEEN :start AND :end")
    List<Lotto> findAllByCreatedAtBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
