package kr.co.polycube.backendtest.winner.entity;

import jakarta.persistence.*;
import kr.co.polycube.backendtest.lotto.entity.Lotto;
import kr.co.polycube.backendtest.user.entity.User;
import lombok.Getter;

@Entity
@Getter
public class Winner extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lotto_id")
    private Lotto lotto;


}
