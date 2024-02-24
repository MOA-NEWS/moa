package com.moa.domain.old;

import com.moa.domain.RoleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private boolean isEnabled = true;

    // 기본생성자 사용 금지
    protected Member(){

    }
    public Member(String name, String password, String role){
        this.name = name;
        this.password = password;
        this.role = role;
    }

}
