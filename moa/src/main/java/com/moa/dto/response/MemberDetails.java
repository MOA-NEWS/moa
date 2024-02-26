package com.moa.dto.response;

import com.moa.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class MemberDetails implements UserDetails {
    private Member member;

    public MemberDetails(Member member) {
        this.member = member;
    }

    // 사용자의 권한목록을 리턴(권한이 여러개일 경우를 대비해 collection 사용), role 값
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        //유저의 role 저장
        collection.add((GrantedAuthority) member::getRole);

        return collection;

    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getName();
    }

    public void setUsername(String username) {
        member.setName(username);
    }
    public Long getId() {
        return member.getId();
    }
    //아래 4개의 옵션은 DB에 추가하지 않았기 때문에 ID 사용을 가능케 하기위해 강제로 true로 바꾼다
    //사용자 ID 만료여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //잠겨있는지
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //사용자의 자격 증명(암호)이 만료되었는지
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //사용자의 사용 가능 여부
    @Override
    public boolean isEnabled() {
        return member.isEnabled();
    }


}
