package kr.co.polycube.backendtest.winner.repository;

import kr.co.polycube.backendtest.winner.entity.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, Long> {
}
