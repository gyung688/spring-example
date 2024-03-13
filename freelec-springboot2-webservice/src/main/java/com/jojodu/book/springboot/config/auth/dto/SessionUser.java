package com.jojodu.book.springboot.config.auth.dto;

import com.jojodu.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    // sessionUser에는 인증된 사용자 정보만 필요하니 이 3개만 필드로 선언한다.
    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}
