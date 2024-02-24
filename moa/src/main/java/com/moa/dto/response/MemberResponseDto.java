package com.moa.dto.response;

import com.moa.domain.RoleStatus;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private Long id;
    private String name;
    private RoleStatus role;
    private Boolean locked = Boolean.FALSE;
//    private String pw;

    public MemberResponseDto(Long id, String name, RoleStatus role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

}

