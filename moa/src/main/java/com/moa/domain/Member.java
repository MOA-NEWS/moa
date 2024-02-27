package com.moa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String password;

    private String role; // ADMIN, USER, ...

    private boolean enabled;

    // 기본생성자 사용 금지
    protected Member() {

    }

    public Member(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.enabled = true;
    }

    // make toString() with String
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
