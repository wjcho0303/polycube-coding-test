package kr.co.polycube.backendtest.lotto.entity;

import jakarta.persistence.*;
import kr.co.polycube.backendtest.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "lottos")
public class Lotto extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_1")
    private int number1;

    @Column(name = "number_2")
    private int number2;

    @Column(name = "number_3")
    private int number3;

    @Column(name = "number_4")
    private int number4;

    @Column(name = "number_5")
    private int number5;

    @Column(name = "number_6")
    private int number6;
}
