package kr.co.polycube.backendtest.user.repository;

import kr.co.polycube.backendtest.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
