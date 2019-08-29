package com.jason.mybatisxml.model;

import com.jason.mybatisxml.enums.UserSexEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class User implements Serializable {

    public static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String passWord;
    private String nickName;
    private UserSexEnum userSex;

    public User() {
        super();
    }

    public User(String userName, String passWord, String nickName, UserSexEnum userSex) {
        super();
        this.userName = userName;
        this.passWord = passWord;
        this.nickName = nickName;
        this.userSex = userSex;
    }

}
