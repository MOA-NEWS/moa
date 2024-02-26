package com.moa.domain;

import lombok.Getter;

@Getter
public enum RoleStatus {
ADMIN("관리자","ROLE_ADMIN"), USER("일반회원","ROLE_USER");


    private final String grade;
    private final String authority;

    RoleStatus(String grade, String authority) {
        this.grade = grade;
        this.authority = authority;
    }
}
