package kr.co.polycube.backendtest.winner.entity;

import jakarta.persistence.*;
import kr.co.polycube.backendtest.lotto.entity.Lotto;
import kr.co.polycube.backendtest.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Winner extends User {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lotto_id")
    private Lotto lotto;

    private int rank;
}
