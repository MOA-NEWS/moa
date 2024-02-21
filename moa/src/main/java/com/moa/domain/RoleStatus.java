package com.moa.domain;

import lombok.Getter;

@Getter
public enum RoleStatus {
    ADMIN("관리자"), USER("일반회원");


    private final String grade;

    RoleStatus(String grade) {
        this.grade = grade;
    }
}
