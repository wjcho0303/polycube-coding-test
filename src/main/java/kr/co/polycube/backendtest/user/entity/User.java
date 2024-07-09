package kr.co.polycube.backendtest.user.entity;

import jakarta.persistence.*;
import kr.co.polycube.backendtest.global.BaseEntity;
import lombok.Getter;

@Entity
@Getter
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
